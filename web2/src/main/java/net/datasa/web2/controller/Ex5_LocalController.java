package net.datasa.web2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
	WebStorage (localStorage & sessionStorage) Test
 */
@Controller
@Slf4j
@RequestMapping("/local")
public class Ex5_LocalController {
	
	@GetMapping("/webStorage")
	public String save() {
		return "localView/1. test";
	}
	
	@GetMapping("/darkMode")
	public String darkMode() {
		return "localView/2. darkmode";
	}
	
	@GetMapping("/tempSave")
	public String temp() {
		return "localView/3. temp";
	}
}
