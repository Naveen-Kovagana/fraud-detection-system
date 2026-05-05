package com.fraud.frauddetection.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FraudResult {

    private String reason;
    private String severity;
}