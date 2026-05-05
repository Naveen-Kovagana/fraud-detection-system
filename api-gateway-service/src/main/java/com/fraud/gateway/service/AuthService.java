package com.fraud.gateway.service;

import com.fraud.gateway.dto.AuthResponse;
import com.fraud.gateway.dto.LoginRequest;
import com.fraud.gateway.entity.RefreshToken;
import com.fraud.gateway.entity.User;
import com.fraud.gateway.repository.RefreshTokenRepository;
import com.fraud.gateway.repository.UserRepository;
import com.fraud.gateway.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final RefreshTokenRepository tokenRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthResponse login(
            LoginRequest request) {

        User user = userRepo
                .findByUsername(
                        request.getUsername())
                .orElseThrow();

        if (!encoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid credentials");
        }

        String access =
                jwtUtil.generateToken(
                        user.getUsername(),
                        user.getRole());

        String refresh =
                UUID.randomUUID().toString();

        RefreshToken token =
                new RefreshToken();

        token.setUsername(
                user.getUsername());

        token.setToken(refresh);

        token.setExpiryAt(
                LocalDateTime.now()
                        .plusDays(7));

        token.setRevoked(false);

        tokenRepo.save(token);

        return new AuthResponse(
                access,
                refresh);
    }

    public AuthResponse refresh(String refreshToken) {

        RefreshToken oldToken = tokenRepo.findByToken(refreshToken)
                .orElseThrow();

        if (oldToken.getRevoked()
                || oldToken.getExpiryAt()
                .isBefore(LocalDateTime.now())) {

            throw new RuntimeException("Invalid refresh token");
        }

        oldToken.setRevoked(true);
        tokenRepo.save(oldToken);

        User user = userRepo.findByUsername(oldToken.getUsername())
                .orElseThrow();
        
        String newAccess = jwtUtil.generateToken(
                user.getUsername(),
                user.getRole());

        String newRefresh = UUID.randomUUID().toString();

        RefreshToken newToken = new RefreshToken();
        newToken.setUsername(user.getUsername());
        newToken.setToken(newRefresh);
        newToken.setExpiryAt(LocalDateTime.now().plusDays(7));
        newToken.setRevoked(false);

        tokenRepo.save(newToken);

        return new AuthResponse(newAccess, newRefresh);
    }

    public void logout(
            String refreshToken) {

        RefreshToken token =
                tokenRepo.findByToken(
                                refreshToken)
                        .orElseThrow();

        token.setRevoked(true);

        tokenRepo.save(token);
    }
}
