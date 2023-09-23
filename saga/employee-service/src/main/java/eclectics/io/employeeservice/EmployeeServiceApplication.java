package eclectics.io.employeeservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner runner() {
		return args -> {
			System.out.println("EMPLOYEE SERVICE STARTED SUCCESSFULLY AT " + new Date());
		};
	}
}
