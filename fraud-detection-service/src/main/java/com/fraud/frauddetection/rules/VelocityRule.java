package com.fraud.frauddetection.rules;

import com.fraud.frauddetection.dto.FraudResult;
import com.fraud.frauddetection.dto.TransactionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VelocityRule implements FraudRule {

    private final StringRedisTemplate redisTemplate;

    private static final int THRESHOLD = 3;
    private static final int WINDOW_SECONDS = 60;

    @Override
    public Optional<FraudResult> evaluate(TransactionEvent event) {

        String key = "txn_count:" + event.getUserId();

        Long count = redisTemplate.opsForValue().increment(key);

        if (count == 1) {
            redisTemplate.expire(key, Duration.ofSeconds(WINDOW_SECONDS));
        }

        if (count != null && count > THRESHOLD) {
            return Optional.of(
                    new FraudResult(
                            "VELOCITY_ATTACK",
                            "HIGH"
                    )
            );
        }

        return Optional.empty();
    }
}