package com.notificationservice.services;

import com.notificationservice.constants.Constants;
import com.notificationservice.constants.SmsStatus;
import com.notificationservice.dto.response.external.ImiApiResponse;
import com.notificationservice.models.elasticsearch.ESSmsRequest;
import com.notificationservice.models.entity.PhoneNumber;
import com.notificationservice.models.entity.SmsRequest;
import com.notificationservice.repositories.BlackListRepository;
import com.notificationservice.repositories.SmsRequestRepository;
import com.notificationservice.repositories.elasticsearch.ESSmsRequestRepository;
import com.notificationservice.services.external.ImiApiService;
import com.notificationservice.transformer.NotificationServiceTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class KafkaConsumerService {

    @Autowired
    private SmsRequestRepository smsRequestRepository;

    @Autowired
    private NotificationServiceTransformer notificationServiceTransformer;

    @Autowired
    private ESSmsRequestRepository esSmsRequestRepository;

    @Autowired
    private BlackListService blackListService;

    @Autowired
    private ImiApiService imiApiService;

    @KafkaListener(topics = Constants.KAFKA_TOPIC, groupId = Constants.KAFKA_GROUP_ID)
    public void consume(String reqId) throws Exception {

        SmsRequest smsRequest = smsRequestRepository.findById(reqId).orElse(null);

        PhoneNumber phoneNumber = blackListService.findById(smsRequest.getPhoneNumber());

        if(Objects.isNull(phoneNumber))
        {
            ResponseEntity<ImiApiResponse> response = imiApiService.makeApiCall(smsRequest);

            log.info("ImiApi response: " + response);

            if(response.getBody().getImiApiResponseDetails().get(0).getCode().equals("1001")) {

                smsRequest.setStatus(SmsStatus.QUEUED);
            }
            else {

                smsRequest.setStatus(SmsStatus.FAILED);
                smsRequest.setFailureCode(response.getBody().getImiApiResponseDetails().get(0).getCode());
                smsRequest.setFailureComments(response.getBody().getImiApiResponseDetails().get(0).getDescription());
            }
        }
        else {

                smsRequest.setStatus(SmsStatus.FAILED);
                smsRequest.setFailureCode("1004");
                smsRequest.setFailureComments("phone_number is blacklisted");
        }

        smsRequestRepository.save(smsRequest);

        // Create index in es
        ESSmsRequest esSmsRequest = notificationServiceTransformer.getESSmsRequest(smsRequest);
        esSmsRequestRepository.save(esSmsRequest);
    }

}
