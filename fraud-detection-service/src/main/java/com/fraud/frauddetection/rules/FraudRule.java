package com.fraud.frauddetection.rules;

import com.fraud.frauddetection.dto.FraudResult;
import com.fraud.frauddetection.dto.TransactionEvent;

import java.util.Optional;

public interface FraudRule {

    Optional<FraudResult> evaluate(TransactionEvent event);
}