package com.fraud.gateway.dto;

import lombok.Data;

@Data
public class RefreshRequest {
    private String refreshToken;
}