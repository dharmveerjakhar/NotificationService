package com.notificationservice.repositories;

import com.notificationservice.models.entity.PhoneNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlackListRepository extends CrudRepository<PhoneNumber, String> {
    List<PhoneNumber> findAll();
}