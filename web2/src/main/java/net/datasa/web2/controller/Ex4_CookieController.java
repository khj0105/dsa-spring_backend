package net.datasa.web2.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/cookie")
public class Ex4_CookieController {
/*
	[ 쿠키 ]
	쿠키는 클라이언트(웹 브라우저)에 저장되는 작은 데이터 조각
	서버가 클라이언트에게 쿠키를 설정하면, 클라이언트는 이후의 요청에서
	이 쿠키를 서버에 다시 전송, 이를 통해 서버는 클라이언트를 식별.
	
	- 저장 위치: 클라이언트의 웹 브라우저에 저장
	- 크기 제한: 일반적으로 하나의 쿠키는 4KB 이하의 데이터를 저장
	- 유효 기간: 만료 날짜가 지나면 쿠키는 삭제
	- 보안: 쿠키는 텍스트 형식으로 저장되므로 보안에 취약
	- 범위: 도메인과 경로를 지정하여 특정 도메인 및 경로에서만 쿠키를 전송하도록 가능.
	ex). 사용자 로그인 정보 유지, 사용자 설정 및 선호 사항 저장, 트래킹 및 분석
 */
	
	@GetMapping("/save")
	public String cookie1(HttpServletResponse response) {
		
		// import jakarta.servlet.http.Cookie
		// 쿠키 생성 (이름=값)
		Cookie c1 = new Cookie("str", "abcde");
		Cookie c2 = new Cookie("num","123");
		
		// 쿠키 수명
		c1.setMaxAge(60*60*24*3);
		c2.setMaxAge(60*60*24*3);
		
		// 쿠키 경로 설정
		c1.setPath("/");
		c2.setPath("/");
		
		// 클라이언트로 쿠키 보내서 저장
		response.addCookie(c1);
		response.addCookie(c2);
		
		return "redirect:/";
	}
	
	// 쿠키 읽기
	@GetMapping("/read")
	public String cookie2(
//			@CookieValue(name = "cookie", defaultValue = "없음") String cookie
			@CookieValue(name = "str", defaultValue = "0") String str
			, @CookieValue(name = "num", defaultValue = "0") int num
	) {
		
		log.debug("=== 쿠키 읽기 ===");
		log.debug("> 쿠키값 str: {}", str);
		log.debug("> 쿠키값 num: {}", num);
		
		return "redirect:/";
	}
	
	// 쿠키 삭제
	@GetMapping("/clear")
	public String cookie3(HttpServletResponse response) {
		
		Cookie c1 = new Cookie("str", null);
		Cookie c2 = new Cookie("num", null);
		c1.setMaxAge(0);
		c2.setMaxAge(0);
		c1.setPath("/");
		c1.setPath("/");
		
		response.addCookie(c1);
		response.addCookie(c2);
		
		return "redirect:/";
	}
}
