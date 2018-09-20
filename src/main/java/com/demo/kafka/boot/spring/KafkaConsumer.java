package com.demo.kafka.boot.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {
    @KafkaListener(topics = "${application.topic.common-error}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        log.trace("Received Messasge: " + message);
    }
}
