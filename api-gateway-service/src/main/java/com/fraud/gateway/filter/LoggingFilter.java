package com.fraud.gateway.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(org.springframework.web.server.ServerWebExchange exchange,
                             org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        String requestPath = exchange.getRequest().getURI().toString();

        System.out.println("➡ Incoming Request: " + requestPath);

        long startTime = System.currentTimeMillis();

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    long duration = System.currentTimeMillis() - startTime;
                    int statusCode = exchange.getResponse().getStatusCode().value();

                    System.out.println("⬅ Response: " + statusCode + " | Time: " + duration + "ms");
                }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}