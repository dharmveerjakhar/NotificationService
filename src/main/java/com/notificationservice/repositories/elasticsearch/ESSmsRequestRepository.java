package com.notificationservice.repositories.elasticsearch;

import com.notificationservice.models.elasticsearch.ESSmsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ESSmsRequestRepository extends ElasticsearchRepository<ESSmsRequest, Integer> {
    Page<ESSmsRequest> findAllByMessageContainsIgnoreCase(String text, Pageable pageable);
    Page<ESSmsRequest> findAllByPhoneNumberAndCreatedAtBetween(String phoneNumber, Date startTime, Date endTime, Pageable pageable);
}