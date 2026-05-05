package com.fraud.frauddetection.service;

import com.fraud.frauddetection.entity.FraudAlert;
import com.fraud.frauddetection.repository.FraudAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudService {

    private final FraudAlertRepository repository;

    public void createAlert(
            String transactionId,
            Double amount,
            String reason,
            String severity
    ) {

        try {
            FraudAlert alert = FraudAlert.builder()
                    .transactionId(transactionId)
                    .amount(amount)
                    .reason(reason)
                    .severity(severity)
                    .createdAt(LocalDateTime.now())
                    .build();

            repository.save(alert);

        } catch (DataIntegrityViolationException ex) {
        }
    }
}