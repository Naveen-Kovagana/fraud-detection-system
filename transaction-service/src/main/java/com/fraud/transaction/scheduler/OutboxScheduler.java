package com.fraud.transaction.scheduler;

import com.fraud.transaction.entity.OutboxEvent;
import com.fraud.transaction.kafka.TransactionProducer;
import com.fraud.transaction.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxScheduler {

    private final OutboxRepository repository;
    private final TransactionProducer producer;

    @Scheduled(fixedRate = 5000)
    public void publishEvents() {

        List<OutboxEvent> events = repository.findByStatus("NEW");

        if (events.isEmpty()) {
            return;
        }

        log.info("Found {} NEW outbox events", events.size());

        for (OutboxEvent event : events) {
            try {
                producer.sendRaw(event.getPayload());

                event.setStatus("SENT");
                repository.save(event);

                log.info("Published event id={}", event.getId());

            } catch (Exception e) {
                log.error("Failed event id={}", event.getId(), e);
            }
        }
    }
}