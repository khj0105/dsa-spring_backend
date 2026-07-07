package net.datasa.web4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
	@SpringBootApplication : 전체 어플리케이션의 설정 시작점
	
	@EnableJpaAuditing
		- JPA Auditing 기능을 활성화
 */
@EnableJpaAuditing
@SpringBootApplication
public class Web4Application {

	public static void main(String[] args) {
		SpringApplication.run(Web4Application.class, args);
	}

}
