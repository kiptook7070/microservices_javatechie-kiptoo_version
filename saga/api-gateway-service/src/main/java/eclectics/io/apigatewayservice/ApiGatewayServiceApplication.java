package eclectics.io.apigatewayservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner runner() {
		return args -> {
			System.out.println("API GATEWAY SERVICE STARTED SUCCESSFULLY AT " + new Date());
		};
	}
}
