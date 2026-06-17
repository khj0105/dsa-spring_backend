package net.datasa.web2.controller;

import lombok.extern.slf4j.Slf4j;
import net.datasa.web2.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
	Lombok Test
*/
@Slf4j
@Controller
public class Ex1_LombokController {
	
	@GetMapping("/lom/lombok")
	public String lombok() {
		
		Person p = new Person();
		p.setId("aaa");
		p.setName("홍길동");
		log.debug("id: {}", p.getId());
		log.debug("name: {}", p.getName());
		log.debug("p: {}", p); // p.toString()은 원래 주소값을 부르지만 @data를 통해 주소값이 아닌 입력값을 받음
		
		Person p2 = new Person("abc", "123", "김철수", "010-1111-2222","KT");
		
		log.debug("p2: {}", p2);
		
		// @Builder : 메서드 체이닝 방식으로 인스턴스 생성
		Person p3 = Person.builder()
				.id("aaaa")
				.name("홍길동")
				.password("123")
				.phone("010-2222-3333")
				.com("SKT")
				.build();
		log.debug("p3: {}", p3);
		
//		return "home";		// forward 요청이 1번 들어옴
		return "redirect:/";	// redirect 요청이 2번 들어옴
	}
	
	// 로그 출력
	@GetMapping("/lom/logger")
	public String logger() {
		
		log.error("error");
		log.warn("warn");
		log.info("info");
		log.debug("debug");
		log.trace("trace");
		
		return "redirect:/";
	}
}
