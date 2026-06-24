package net.datasa.web2.controller;

import lombok.extern.slf4j.Slf4j;
import net.datasa.web2.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	// ---------------------------------------------------------------------------------
	@GetMapping("/loop")
	public String loop(Model model) {
		
		log.debug("--- 타임리프 반복문(List, Map) 예제 ---");
		
		// 리스트 데이터 생성 - (collection은 제네릭 타입 사용)
		List<String> strList = new ArrayList<>(List.of("JAVA", "DB", "HTML", "CSS", "JS"));
		
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("aaa", "111", "a", "010-1111-2222", "SKT"));
		personList.add(new Person("bbb", "111", "b", "010-2222-3333", "KT"));
		personList.add(new Person("ccc", "111", "c", "010-3333-4444", "LGU+"));
		personList.add(new Person("ddd", "111", "d", "010-4444-5555", "KT"));
		
		model.addAttribute("strList", strList);
		model.addAttribute("personList", personList);
		
		// 맵 데이터 생성
		Map<String, Object> productMap = new HashMap<>();
		productMap.put("product", "노트북");
		productMap.put("price", 1500000);
		productMap.put("brand", "삼성");
		
		model.addAttribute("map", productMap);
	
		return "thymeleafView/4. loop";
	}
}
