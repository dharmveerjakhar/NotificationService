package com.notificationservice.config;

import com.notificationservice.constants.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value(Constants.Config.CONNECTION_TIMEOUT)
    private Integer connectTimeOut;
    @Value(Constants.Config.READ_TIMEOUT)
    private Integer readTimeOut;
    @Bean
    public RestTemplate customRestTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(connectTimeOut);
        httpRequestFactory.setReadTimeout(readTimeOut);
        return new RestTemplate(httpRequestFactory);
    }
}