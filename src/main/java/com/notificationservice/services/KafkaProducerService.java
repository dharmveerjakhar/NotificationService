package com.notificationservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {

    @Autowired
    public KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String reqId) {

        kafkaTemplate.send(topic, reqId);
    }

}
