package com.fraud.frauddetection.rules;

import com.fraud.frauddetection.dto.FraudResult;
import com.fraud.frauddetection.dto.TransactionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FraudRuleEngine {

    private final List<FraudRule> rules;

    public List<FraudResult> evaluate(TransactionEvent event) {

        return rules.stream()
                .map(rule -> rule.evaluate(event))
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .toList();
    }
}