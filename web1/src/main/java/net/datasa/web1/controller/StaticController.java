package net.datasa.web1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class StaticController {
	
	// http://localhost:9991/image 요청보냈을때 매핑되는 메서드 (아래 코드)
	@GetMapping("/image")
	public String image() {
		log.debug("이미지 경로 요청 처리");
		
		// viewResolver를 호출해서 html을 찾으러 감.
		//	Prefix + "image" + Suffix
		//	Prefix(접두사) : Spring Boot 기본값은 classpath:/templates/
		//	Suffix(접미사) : Spring Boot 기본값은 .html
		return "image";
	}
	
	// 경로값을 추가하는 이유: 1. 자원의 그룹화, 2. 보안 및 권한 설정
	@GetMapping("/sub/image")
	public String image2() {
		return "image";
	}
	
	// CSS ------------------------------------------------------
	@GetMapping("/css")
	public String css() {
		return "css";
	}
	
	// JS -------------------------------------------------------
	@GetMapping("/js")
	public String js() {
		return "js";		// js.html
	}
	
	// PATH -------------------------------------------------------
	@GetMapping("/path")
	public String path() {
		return "path";		// templates/path.html
	}
	
	@GetMapping("/sub/path")
	public String subPath() {
		return "sub/path2";
		/*
			templates/sub/path2.html
		 */
	}
	
}
