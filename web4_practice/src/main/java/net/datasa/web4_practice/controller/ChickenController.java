package net.datasa.web4_practice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ChickenController {
	@GetMapping("/chicken/order")
	public String chicken() {
		return "/chicken/order";
	}
}
