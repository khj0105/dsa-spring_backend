package net.datasa.web1;

import  org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @ComponentScan : 현재 패키지(net.datasa.web1)와 그 하위 패키지들을 뒤져서
// @Controller, @Service, @Repository 등이 붙은 클래스를 찾아 객체로 등록
@SpringBootApplication
public class Web1Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Web1Application.class, args);
	}

}
