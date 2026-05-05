package com.fraud.frauddetection.rules;

import com.fraud.frauddetection.dto.FraudResult;
import com.fraud.frauddetection.dto.TransactionEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HighAmountRule implements FraudRule {

    @Override
    public Optional<FraudResult> evaluate(TransactionEvent event) {

        if (event.getAmount() >= 50000) {
            return Optional.of(
                    new FraudResult(
                            "HIGH_AMOUNT",
                            "HIGH"
                    )
            );
        }

        return Optional.empty();
    }
}