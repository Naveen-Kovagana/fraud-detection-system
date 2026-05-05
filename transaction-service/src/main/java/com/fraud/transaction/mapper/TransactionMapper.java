package com.fraud.transaction.mapper;

import com.fraud.transaction.dto.TransactionRequest;
import com.fraud.transaction.dto.TransactionResponse;
import com.fraud.transaction.entity.Transaction;

import java.time.LocalDateTime;

public class TransactionMapper {

    private TransactionMapper() {
    }

    public static Transaction toEntity(TransactionRequest request) {

        Transaction txn = new Transaction();

        txn.setTransactionId(request.getTransactionId());
        txn.setUserId(request.getUserId());
        txn.setAmount(request.getAmount());
        txn.setCurrency(request.getCurrency());

        txn.setStatus("SUCCESS");
        txn.setCreatedAt(LocalDateTime.now());

        return txn;
    }

    public static TransactionResponse toResponse(Transaction txn) {

        return TransactionResponse.builder()
                .transactionId(txn.getTransactionId())
                .userId(txn.getUserId())
                .amount(txn.getAmount())
                .currency(txn.getCurrency())
                .status(txn.getStatus())
                .createdAt(txn.getCreatedAt())
                .build();
    }
}