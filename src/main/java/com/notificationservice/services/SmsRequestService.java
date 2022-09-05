package com.notificationservice.services;

import com.notificationservice.constants.Constants;
import com.notificationservice.constants.SmsStatus;
import com.notificationservice.dto.request.SendSmsRequest;
import com.notificationservice.models.entity.SmsRequest;
import com.notificationservice.repositories.SmsRequestRepository;
import com.notificationservice.dto.response.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class SmsRequestService {

    @Autowired
    public SmsRequestRepository smsRequestRepository;

    @Autowired
    public KafkaProducerService kafkaProducerService;

    final String topic = Constants.KAFKA_TOPIC;

    public SendSmsResponse sendSms(SendSmsRequest requestParams) {

        UUID uuid = UUID.randomUUID();

        SmsRequest smsRequest = SmsRequest.builder()
                .id(uuid.toString())
                .phoneNumber(requestParams.getPhoneNumber())
                .message(requestParams.getMessage())
                .status(SmsStatus.PROCESSING).build();

        log.info(smsRequest.toString());

        smsRequestRepository.save(smsRequest);
        kafkaProducerService.sendMessage(topic, smsRequest.getId());

        return new SendSmsResponse(smsRequest.getId(), "Successfully Processed!");
    }

    public SmsRequest findById(String id) throws Exception {

        return smsRequestRepository.findById(id).orElse(null);
    }
}
