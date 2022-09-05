package com.notificationservice.services;

import com.notificationservice.dto.request.GetByTextRequest;
import com.notificationservice.dto.request.GetByTimeWindowRequest;
import com.notificationservice.models.elasticsearch.ESSmsRequest;
import com.notificationservice.models.entity.SmsRequest;
import com.notificationservice.repositories.SmsRequestRepository;
import com.notificationservice.repositories.elasticsearch.ESSmsRequestRepository;
import com.notificationservice.transformer.NotificationServiceTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ESSmsRequestService {

    @Autowired
    private ESSmsRequestRepository esSmsRequestRepository;

    @Autowired
    private SmsRequestRepository smsRequestRepository;

    @Autowired
    private NotificationServiceTransformer notificationServiceTransformer;

    public List<SmsRequest> findAllByMessageContainingText(GetByTextRequest requestParams) throws Exception {

        Pageable pageable = PageRequest.of(requestParams.getPageNumber(), requestParams.getPageSize());
        Page<ESSmsRequest> esSmsPage = esSmsRequestRepository.findAllByMessageContainsIgnoreCase(requestParams.getText(), pageable);
        List<ESSmsRequest> esSmsRequestList = esSmsPage.getContent();

        return notificationServiceTransformer.getSmsRequestList(esSmsRequestList);
    }

    public List<SmsRequest> findAllByPhoneNumberInTimeWindow(GetByTimeWindowRequest requestParams) throws Exception {

        Pageable pageable = PageRequest.of(requestParams.getPageNumber(), requestParams.getPageSize());

        List<ESSmsRequest> esSmsRequestList = esSmsRequestRepository.findAllByPhoneNumberAndCreatedAtBetween(
                                                        requestParams.getPhoneNumber(),
                                                        requestParams.getStartTime(),
                                                        requestParams.getEndTime(),
                                                        pageable).getContent();

        return notificationServiceTransformer.getSmsRequestList(esSmsRequestList);
    }
}
