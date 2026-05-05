package com.fraud.gateway.filter;

import com.fraud.gateway.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RoleAuthorizationFilter implements GlobalFilter {

    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {

        String path = exchange.getRequest()
                .getURI()
                .getPath();

        HttpMethod method = exchange.getRequest().getMethod();

        if (path.startsWith("/auth")) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized(exchange);
        }

        String token = authHeader.substring(7);

        String role;
        try {
            role = jwtUtil.extractClaims(token)
                    .get("role", String.class);
        } catch (Exception e) {
            return unauthorized(exchange);
        }

        if (!isAllowed(path, method, role)) {
            return forbidden(exchange);
        }

        return chain.filter(exchange);
    }

    private boolean isAllowed(String path,
                              HttpMethod method,
                              String role) {

        if (path.startsWith("/transactions")) {

            if (method == HttpMethod.POST) {
                return role.equals("USER")
                        || role.equals("ADMIN");
            }

            if (method == HttpMethod.GET) {
                return role.equals("ADMIN");
            }
        }

        if (path.startsWith("/frauds")) {

            if (method == HttpMethod.GET) {
                return role.equals("ADMIN")
                        || role.equals("ANALYST");
            }
        }

        return false;
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse()
                .setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    private Mono<Void> forbidden(ServerWebExchange exchange) {
        exchange.getResponse()
                .setStatusCode(HttpStatus.FORBIDDEN);
        return exchange.getResponse().setComplete();
    }
}