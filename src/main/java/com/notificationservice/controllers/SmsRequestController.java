package com.notificationservice.controllers;

import com.notificationservice.constants.Constants;
import com.notificationservice.dto.request.SendSmsRequest;
import com.notificationservice.models.entity.SmsRequest;
import com.notificationservice.services.SmsRequestService;
import com.notificationservice.dto.response.SendSmsResponse;
import com.notificationservice.dto.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.Endpoint.BASE_PATH)
@Slf4j
public class SmsRequestController {

    @Autowired
    public SmsRequestService smsRequestService;

    @PostMapping(Constants.Endpoint.SEND_SMS)
    public GenericResponse<SendSmsResponse> sendSms(
                    @Valid @RequestBody SendSmsRequest sendSmsRequest) {

        return new GenericResponse<>(smsRequestService.sendSms(sendSmsRequest), null, null);
    }

    @GetMapping(value = {Constants.Endpoint.GET_SMS_REQUEST, Constants.Endpoint.GET_SMS_REQUEST_WITH_ID_PARAM})
    public GenericResponse<SmsRequest> getSmsRequestDetailsById(
                    @PathVariable String reqId) throws Exception {

        return new GenericResponse<>(smsRequestService.findById(reqId), null, null);
    }
}