package com.fraud.gateway.controller;

import com.fraud.gateway.dto.AuthResponse;
import com.fraud.gateway.dto.LoginRequest;
import com.fraud.gateway.dto.RefreshRequest;
import com.fraud.gateway.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest req) {

        return service.login(req);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(
            @RequestBody RefreshRequest req) {

        return service.refresh(
                req.getRefreshToken());
    }

    @PostMapping("/logout")
    public String logout(
            @RequestBody RefreshRequest req) {

        service.logout(
                req.getRefreshToken());

        return "Logged out";
    }
}