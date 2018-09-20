package com.demo.kafka.boot.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class KafkaConsumer {
    @KafkaListener(topics = "${application.topic.common-error}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(final ConsumerRecord<String, String> consumerRecord) throws IOException {
        log.trace("Received Messasge: " + consumerRecord.value());
    }
}
