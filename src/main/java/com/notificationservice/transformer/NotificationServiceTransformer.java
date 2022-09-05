package com.notificationservice.transformer;

import com.notificationservice.models.elasticsearch.ESSmsRequest;
import com.notificationservice.models.entity.SmsRequest;
import com.notificationservice.repositories.SmsRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceTransformer {

    @Autowired
    SmsRequestRepository smsRequestRepository;

    public ESSmsRequest getESSmsRequest(SmsRequest smsRequest) {

        return ESSmsRequest.builder()
                .id(smsRequest.getId())
                .phoneNumber(smsRequest.getPhoneNumber())
                .message(smsRequest.getMessage())
                .createdAt(smsRequest.getCreatedAt()).build();
    }

    public List<SmsRequest> getSmsRequestList(List<ESSmsRequest> esSmsRequestList) {
        List<SmsRequest> smsRequestList = new ArrayList<>();
        esSmsRequestList.forEach(esSmsRequest -> {
            String id = esSmsRequest.getId();
            SmsRequest smsRequest = smsRequestRepository.findById(id).orElse(null);
            smsRequestList.add(smsRequest);
        });
        return smsRequestList;
    }
}
