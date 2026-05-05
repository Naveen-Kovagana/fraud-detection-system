package com.fraud.frauddetection.controller;

import com.fraud.frauddetection.entity.FraudAlert;
import com.fraud.frauddetection.repository.FraudAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/frauds")
@RequiredArgsConstructor
public class FraudController {

    private final FraudAlertRepository repository;

    @GetMapping
    public List<FraudAlert> getAll() {
        return repository.findAll();
    }
}
