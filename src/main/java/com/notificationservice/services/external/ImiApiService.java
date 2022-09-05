package com.notificationservice.services.external;

import com.notificationservice.constants.Constants;
import com.notificationservice.dto.request.external.ImiApiChannels;
import com.notificationservice.dto.request.external.ImiApiDestination;
import com.notificationservice.dto.request.external.ImiApiSms;
import com.notificationservice.dto.request.external.ImiApiRequest;
import com.notificationservice.dto.response.external.ImiApiResponse;
import com.notificationservice.models.entity.SmsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

@Service
@Slf4j
public class ImiApiService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<ImiApiResponse> makeApiCall(SmsRequest smsRequest) throws Exception {

        String url = Constants.ImiApi.API_URL;
        String apiKey = Constants.ImiApi.API_KEY;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.set("Key", apiKey);

        ImiApiSms imiApiSms = new ImiApiSms(smsRequest.getMessage());
        ImiApiChannels imiApiChannels = new ImiApiChannels(imiApiSms);
        ImiApiDestination imiApiDestination = new ImiApiDestination(Collections.singletonList(smsRequest.getPhoneNumber()));
        ImiApiRequest imiApiRequest = new ImiApiRequest("sms", imiApiChannels, Collections.singletonList(imiApiDestination));

        HttpEntity<ImiApiRequest> entity = new HttpEntity<>(imiApiRequest, httpHeaders);

        ImiApiResponse response = restTemplate.exchange(url, HttpMethod.POST, entity, ImiApiResponse.class).getBody();

        if(Objects.isNull(response)) {
            log.info("ImiApi response is null");
            throw new Exception("ImiApi response is null");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}