package com.fraud.transaction.service;

import com.fraud.transaction.dto.TransactionRequest;
import com.fraud.transaction.dto.TransactionResponse;
import com.fraud.transaction.entity.OutboxEvent;
import com.fraud.transaction.entity.Transaction;
import com.fraud.transaction.mapper.TransactionMapper;
import com.fraud.transaction.repository.OutboxRepository;
import com.fraud.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;
    private final OutboxRepository outboxRepository;

    public TransactionResponse createTransaction(TransactionRequest request) {

        Transaction txn = TransactionMapper.toEntity(request);

        Transaction saved = repository.save(txn);

        String payload = "{ " +
                "\"transactionId\": \"" + saved.getTransactionId() + "\", " +
                "\"userId\": \"" + saved.getUserId() + "\", " +
                "\"amount\": " + saved.getAmount() + ", " +
                "\"currency\": \"" + saved.getCurrency() + "\", " +
                "\"status\": \"" + saved.getStatus() + "\"" +
                "}";

        OutboxEvent event = OutboxEvent.builder()
                .eventType("TRANSACTION_CREATED")
                .payload(payload)
                .status("NEW")
                .createdAt(LocalDateTime.now())
                .build();

        outboxRepository.save(event);

        return TransactionMapper.toResponse(saved);
    }

    public List<TransactionResponse> getAllTransactions() {
        return repository.findAll()
                .stream()
                .map(TransactionMapper::toResponse)
                .toList();
    }
}