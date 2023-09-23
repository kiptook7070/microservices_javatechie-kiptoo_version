package eclectics.io.apigatewayservice.filter;

import eclectics.io.apigatewayservice.util.JwtUtil;
import jakarta.ws.rs.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    //    @Autowired
//    private RestTemplate template;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException(String.valueOf(HttpStatus.SC_UNAUTHORIZED));
                } else {
                    String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                    System.out.println("Headers "+ authHeader);
                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
                        authHeader = authHeader.substring(7);
                    } else {
                        try {
                            jwtUtil.validateToken(authHeader);
                            System.out.println("MY TOKEN" +jwtUtil);

                        } catch (Exception e) {
                            System.out.println("invalid access...!");
                            throw new RuntimeException(String.valueOf(HttpStatus.SC_UNAUTHORIZED));
                        }
                    }

                }


            }else {
                log.info("Rejected request that is only allowed against master nodes. Returning HTTP 403.");
                throw new ForbiddenException("Request is only allowed against master nodes.");
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
