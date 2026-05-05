package com.fraud.transaction.dto;

import lombok.Data;

@Data
public class TransactionRequest {
    private String transactionId;
    private String userId;
    private Double amount;
    private String currency;
}