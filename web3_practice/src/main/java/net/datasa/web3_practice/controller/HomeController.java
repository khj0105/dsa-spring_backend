package net.datasa.web3_practice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

	@GetMapping({"", "/"})
	public String home() {
		log.debug("홈 화면 접속 -> 학생 목록으로 이동");
		return "redirect:/student/list";
	}
}
