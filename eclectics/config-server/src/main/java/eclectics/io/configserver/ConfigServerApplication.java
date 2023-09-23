package eclectics.io.configserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
	@Bean
	CommandLineRunner runner() {
		return args -> {
			System.out.println("CONFIGURATION SERVER STARTED SUCCESSFULLY AT " + new Date());
		};
	}
}
