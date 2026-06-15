package net.datasa.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 이 클래스가 웹 요청을 처리하는 컨트롤러임을 Spring에게 알려줌
@Controller
public class HomeController {
	
	// http://localhost:9991 경로의 요청을 하기 메서드와 매핑
	@GetMapping({"", "/"})
	public String home() {
		
		System.out.println("Home Controller 실행");
		
		// "home" 을 반환하면 templates/home.html 파일을 찾아 화면에 표시
		return "home";
	}
}
