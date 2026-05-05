package com.fraud.frauddetection.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEvent {

    private String transactionId;
    private String userId;
    private Double amount;
    private String currency;
    private String status;
}