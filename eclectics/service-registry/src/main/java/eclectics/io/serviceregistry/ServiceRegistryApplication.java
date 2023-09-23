package eclectics.io.serviceregistry;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistryApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            System.out.println("SERVICE REGISTRY STARTED SUCCESSFULLY AT " + new Date());
        };
    }

}
