package net.datasa.web4_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Web4PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Web4PracticeApplication.class, args);
	}

}
