package com.fraud.frauddetection.repository;

import com.fraud.frauddetection.entity.FraudAlert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudAlertRepository
        extends JpaRepository<FraudAlert, Long> {
}