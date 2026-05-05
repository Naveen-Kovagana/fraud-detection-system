package com.fraud.frauddetection.rules;

import com.fraud.frauddetection.dto.FraudResult;
import com.fraud.frauddetection.dto.TransactionEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoundAmountRule implements FraudRule {

    @Override
    public Optional<FraudResult> evaluate(TransactionEvent event) {

        double amount = event.getAmount();

        if (amount == 100000 || amount == 50000) {
            return Optional.of(
                    new FraudResult(
                            "ROUND_AMOUNT",
                            "LOW"
                    )
            );
        }

        return Optional.empty();
    }
}