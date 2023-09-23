package eclectics.io.authservice.utils.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    @Value("${spring.application.client.origin_local_server}")
    private String origin_local_server;
    @Value("${spring.application.client.origin_local}")
    private String origin_local;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/api/v1/auth/**")
                                .permitAll()
                                .anyRequest()
                                .fullyAuthenticated())
                .sessionManagement(smc -> smc
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.setMaxAge(4000000L);
        config.addAllowedOrigin(origin_local);
        config.addAllowedOrigin(origin_local_server);
        config.addAllowedHeader("Content-Type");
        config.addAllowedHeader("x-xsrf-token");
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("Access-Control-Allow-Headers");
        config.addAllowedHeader("Origin");
        config.addAllowedHeader("Accept");
        config.addAllowedHeader("X-Requested-With");
        config.addAllowedHeader("Access-Control-Request-Method");
        config.addAllowedHeader("Access-Control-Request-Headers");
        config.addExposedHeader("Content-Type");
        config.addExposedHeader("x-xsrf-token");
        config.addExposedHeader("Authorization");
        config.addExposedHeader("Access-Control-Allow-Headers");
        config.addExposedHeader("Origin");
        config.addExposedHeader("Accept");
        config.addExposedHeader("X-Requested-With");
        config.addExposedHeader("Access-Control-Request-Method");
        config.addExposedHeader("Access-Control-Request-Headers");
        config.addExposedHeader("Message");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
