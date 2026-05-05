package com.fraud.frauddetection.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "fraud_alerts",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"transactionId", "reason"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FraudAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId;

    private Double amount;

    private String reason;

    private String severity;

    private LocalDateTime createdAt;
}