package eclectics.io.authservice;

import eclectics.io.authservice.users.User;
import eclectics.io.authservice.users.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Objects;

@SpringBootApplication
public class AuthServiceApplication {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public AuthServiceApplication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

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
