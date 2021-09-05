package com.ohmynone.gateway.filter;

import com.ohmynone.gateway.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class JwtFilter implements GatewayFilterFactory<JwtFilter.Config> {

    private static final String X_USER = "x-user";

    JwtTokenUtil jwtTokenUtil;

    public JwtFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            final String Auth = getAuthHeader(request);
            final String token = Auth.substring(7);
            try {
                if (!jwtTokenUtil.validateToken(token)) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }

                request.mutate()
                        .header(X_USER, jwtTokenUtil.getUuid(token))
                        .build();

                return chain.filter(exchange.mutate().request(request).build());

            } catch (ExpiredJwtException e) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        };
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    static class Config {

        private String roles;

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }
    }

}
