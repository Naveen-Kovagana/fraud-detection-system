package com.fraud.frauddetection.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fraud.frauddetection.dto.FraudResult;
import com.fraud.frauddetection.dto.TransactionEvent;
import com.fraud.frauddetection.rules.FraudRuleEngine;
import com.fraud.frauddetection.service.FraudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FraudConsumer {

    private final ObjectMapper objectMapper;
    private final FraudRuleEngine ruleEngine;
    private final FraudService fraudService;

    @KafkaListener(
            topics = "transaction-events",
            groupId = "fraud-group"
    )
    public void consume(String message) {

        try {
            TransactionEvent event =
                    objectMapper.readValue(
                            message,
                            TransactionEvent.class
                    );

            List<FraudResult> results =
                    ruleEngine.evaluate(event);

            for (FraudResult result : results) {

                fraudService.createAlert(
                        event.getTransactionId(),
                        event.getAmount(),
                        result.getReason(),
                        result.getSeverity()
                );
            }

            log.info(
                    "Processed transaction {}",
                    event.getTransactionId()
            );

        } catch (Exception e) {
            log.error("Consumer failed", e);
        }
    }
}