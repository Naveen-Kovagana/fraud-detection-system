package com.fraud.transaction.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEvent {
    private String transactionId;
    private String userId;
    private Double amount;
    private String currency;
    private String status;
}