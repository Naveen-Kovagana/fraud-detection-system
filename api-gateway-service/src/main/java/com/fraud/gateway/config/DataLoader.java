package com.fraud.gateway.config;

import com.fraud.gateway.entity.User;
import com.fraud.gateway.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader
        implements CommandLineRunner {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {

        if(repo.findByUsername("admin").isEmpty()) {

            save("admin",
                    "admin123",
                    "ADMIN");

            save("user",
                    "user123",
                    "USER");

            save("analyst",
                    "analyst123",
                    "ANALYST");
        }
    }

    private void save(
            String username,
            String password,
            String role) {

        User u = new User();

        u.setUsername(username);
        u.setPassword(
                encoder.encode(password));
        u.setRole(role);
        u.setEnabled(true);

        repo.save(u);
    }
}
