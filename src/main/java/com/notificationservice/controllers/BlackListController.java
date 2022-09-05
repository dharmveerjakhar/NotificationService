package com.notificationservice.controllers;

import com.notificationservice.constants.Constants;
import com.notificationservice.dto.request.BlackListRequest;
import com.notificationservice.dto.response.BlackListResponse;
import com.notificationservice.services.BlackListService;
import com.notificationservice.dto.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(Constants.Endpoint.BASE_PATH)
public class BlackListController {

    @Autowired
    private BlackListService blackListService;

    @PostMapping(Constants.Endpoint.BLACKLIST_CRUD)
    public GenericResponse<String> addPhoneNumbersToBlacklist(
            @Valid @RequestBody BlackListRequest blackListRequest) throws Exception {

        return new GenericResponse<>(blackListService.addPhoneNumbersToBlacklist(blackListRequest), null, null);
    }

    @DeleteMapping(Constants.Endpoint.BLACKLIST_CRUD)
    public GenericResponse<String> removePhoneNumbersFromBlacklist(
            @Valid @RequestBody BlackListRequest blackListRequest) {

        return new GenericResponse<>(blackListService.removePhoneNumbersFromBlacklist(blackListRequest), null, null);
    }

    @GetMapping(Constants.Endpoint.BLACKLIST_CRUD)
    public GenericResponse<BlackListResponse> getAllBlacklistedNumbers() throws Exception {

        return new GenericResponse<>(blackListService.getAllBlacklistedNumbers(), null, null);
    }
}
