package eclectics.io.authservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner runner() {
		return args -> {
			System.out.println("AUTH SERVICE STARTED SUCCESSFULLY AT " + new Date());
		};
	}
}
