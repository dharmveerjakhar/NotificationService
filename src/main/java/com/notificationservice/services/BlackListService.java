package com.notificationservice.services;

import com.notificationservice.dto.request.BlackListRequest;
import com.notificationservice.dto.response.BlackListResponse;
import com.notificationservice.models.entity.PhoneNumber;
import com.notificationservice.repositories.BlackListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Slf4j
public class BlackListService {

    @Autowired
    private BlackListRepository blackListRepository;

    @Autowired
    private RedisBlackListService redisBlackListService;

    public PhoneNumber findById(String phoneNumber) {

        if(Boolean.TRUE.equals(redisBlackListService.hasKey(phoneNumber))) {
            log.info("phone_number found in redis cache");
            return new PhoneNumber(phoneNumber);
        }
        log.info("fetched from mysql db");
        return blackListRepository.findById(phoneNumber).orElse(null);
    }

    public String addPhoneNumbersToBlacklist(BlackListRequest blackListRequest) throws Exception {

        log.info(blackListRequest.toString());
        blackListRequest.getPhoneNumbers().forEach(phoneNumber -> {
            PhoneNumber phNumber = findById(phoneNumber);
            if(Objects.isNull(phNumber)) {
                redisBlackListService.save(phoneNumber, "");
                blackListRepository.save(new PhoneNumber(phoneNumber));
            }
        });
        return "Successfully blacklisted";
    }

    public String removePhoneNumbersFromBlacklist(BlackListRequest blackListRequest) {

        List<String> phoneNumbers = blackListRequest.getPhoneNumbers();
        phoneNumbers.forEach(phoneNumber -> {
            PhoneNumber phNumber = findById(phoneNumber);
            if(Objects.nonNull(phNumber)) {
                redisBlackListService.deleteByKey(phNumber.getPhoneNumber());
                blackListRepository.deleteById(phNumber.getPhoneNumber());
            }
        });
        return "Successfully whitelisted";
    }

    public BlackListResponse getAllBlacklistedNumbers() throws Exception {

        Set<String> phoneNumbersSet = redisBlackListService.getKeys();
        return BlackListResponse.builder().phoneNumbers(new ArrayList<>(phoneNumbersSet)).build();
    }
}
