package net.datasa.web2.controller;

import lombok.extern.slf4j.Slf4j;
import net.datasa.web2.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

/*
	Thymeleaf Test
 */
@Controller
@Slf4j
@RequestMapping("/thymeleaf")
public class Ex6_ThymeleafController {
	
	@GetMapping("/basic")
	public String basic(Model model) {
		log.debug("--- 타임리프 기본 출력 및 경로 예제 ---");
		
		model.addAttribute("str", "홍길동");
		model.addAttribute("num", 100);
		model.addAttribute("tag", "<marquee>html태그</marquee>");
		
		return "thymeleafView/1. basic";
	}
	
	// --------------------------------------------------------------------------------
	@GetMapping("/operator")
	public String operator(Model model) {
		log.debug("--- 타임리프 연산 및 포매팅 예제 ---");
		
		model.addAttribute("str", "홍길동");
		model.addAttribute("num", 50);
		model.addAttribute("inum", 123456789);
		model.addAttribute("dnum", "1234.5678");
		model.addAttribute("localDateTime", LocalDateTime.now());
		
		return "thymeleafView/2. operator";
	}
	
	// --------------------------------------------------------------------------------
	@GetMapping("/object")
	public String object(Model model) {
		
		log.debug("--- 타임리프 객체 및 조건문 예제 ---");
		Person p = new Person("hong123", "111", "홍길동", "010-1111-2222", "SKT");
		
		model.addAttribute("person", p);
		model.addAttribute("num, 75");
		
		return "thymeleafView/3. object";
	}
}
