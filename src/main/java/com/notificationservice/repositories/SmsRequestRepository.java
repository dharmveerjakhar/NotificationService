package com.notificationservice.repositories;

import com.notificationservice.models.entity.SmsRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRequestRepository extends CrudRepository<SmsRequest, String> {
}