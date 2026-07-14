package net.datasa.ex4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Ex4Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex4Application.class, args);
	}

}
