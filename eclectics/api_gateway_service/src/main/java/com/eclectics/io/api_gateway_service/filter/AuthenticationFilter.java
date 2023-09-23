package com.eclectics.io.api_gateway_service.filter;

import com.eclectics.io.api_gateway_service.util.JwtUtil;
import jakarta.ws.rs.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private RestTemplate template;
//    @Autowired
//    private JwtUtil jwtUtil;
    @Autowired
    private TokenProvider tokenProvider;

    public AuthenticationFilter() {
        super(Config.class);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException(String.valueOf(HttpStatus.SC_UNAUTHORIZED));
                } else {
                    String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                    LOGGER.info("Headers " + authHeader);
                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
                        authHeader.substring(7);
                    } else {
                        try {
                            tokenProvider.validateToken(authHeader);
                            LOGGER.info("MY TOKEN" + tokenProvider);
                        } catch (Exception e) {
                            System.out.println("invalid access...!");
                            throw new RuntimeException(String.valueOf(HttpStatus.SC_UNAUTHORIZED));
                        }
                    }

                }


            } else {
                log.info("Rejected request that is only allowed against master nodes. Returning HTTP 403.");
                throw new ForbiddenException("Request is only allowed against master nodes.");
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
