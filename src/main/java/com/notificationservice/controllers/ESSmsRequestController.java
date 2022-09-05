package com.notificationservice.controllers;

import com.notificationservice.constants.Constants;
import com.notificationservice.dto.request.GetByTextRequest;
import com.notificationservice.dto.request.GetByTimeWindowRequest;
import com.notificationservice.dto.response.GenericResponse;
import com.notificationservice.models.entity.SmsRequest;
import com.notificationservice.services.ESSmsRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(Constants.Endpoint.BASE_PATH)
public class ESSmsRequestController {

    @Autowired
    ESSmsRequestService esSmsRequestService;

    @GetMapping(Constants.Endpoint.GET_ALL_SMS_REQUEST_BY_MESSAGE_CONTAINING_TEXT)
    public GenericResponse<List<SmsRequest>> getSmsRequestDetailsContainingText(
                    @Valid @RequestBody GetByTextRequest requestParams) throws Exception {
        return new GenericResponse<>(esSmsRequestService.findAllByMessageContainingText(requestParams), null, null);
    }

    @GetMapping(Constants.Endpoint.GET_ALL_SMS_REQUEST_BY_PHONE_NUMBER_IN_TIME_WINDOW)
    public GenericResponse<List<SmsRequest>> getSmsRequestDetailsByPhoneNumberInTimeWindow(
                    @Valid @RequestBody GetByTimeWindowRequest requestParams) throws Exception {
        return new GenericResponse<>(esSmsRequestService.findAllByPhoneNumberInTimeWindow(requestParams), null, null);
    }
}
