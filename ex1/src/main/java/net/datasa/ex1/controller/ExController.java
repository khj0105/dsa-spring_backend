package net.datasa.ex1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class ExController {

	// code here
	@GetMapping("/introduce")
	public String introduce() {
		return "/ex/introduce";
	}
	
	@GetMapping("/notice")
	public String notice() {
		return "ex/notice";
	}
	
	@GetMapping("/inquiry")
	public String inqure() {
		return  "/ex/inquiry";
	}
	
	@PostMapping("/sendData")
	public String inquiry(
			@RequestParam(name = "name") String name
			, @RequestParam(name = "email") String email
			, @RequestParam(name = "message") String message
	) {
		
		log.debug("> 문의 - 이름: {}, 이메일: {}, 문의 내용: {}", name, email, message);
		
		
		return "redirect:/";
	}
}
