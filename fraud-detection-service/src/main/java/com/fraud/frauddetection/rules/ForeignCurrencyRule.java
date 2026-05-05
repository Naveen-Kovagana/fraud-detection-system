package com.fraud.frauddetection.rules;

import com.fraud.frauddetection.dto.FraudResult;
import com.fraud.frauddetection.dto.TransactionEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ForeignCurrencyRule implements FraudRule {

    @Override
    public Optional<FraudResult> evaluate(TransactionEvent event) {

        if (!"INR".equalsIgnoreCase(event.getCurrency())) {
            return Optional.of(
                    new FraudResult(
                            "FOREIGN_CURRENCY",
                            "MEDIUM"
                    )
            );
        }

        return Optional.empty();
    }
}