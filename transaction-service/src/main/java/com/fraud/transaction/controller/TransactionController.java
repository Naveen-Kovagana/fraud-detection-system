package com.fraud.transaction.controller;

import com.fraud.transaction.dto.TransactionRequest;
import com.fraud.transaction.dto.TransactionResponse;
import com.fraud.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    public TransactionResponse create(@RequestBody TransactionRequest request) {
        return service.createTransaction(request);
    }

    @GetMapping
    public List<TransactionResponse> getAll() {
        return service.getAllTransactions();
    }
}