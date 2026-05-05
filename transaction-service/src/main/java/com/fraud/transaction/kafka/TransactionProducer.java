package com.fraud.transaction.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "transaction-events";

    public void sendRaw(String payload) {

        try {
            kafkaTemplate.send(TOPIC, payload);
            System.out.println("Event sent to Kafka: " + payload);

        } catch (Exception e) {
            System.out.println("Kafka Failed: " + e.getMessage());
        }
    }
}