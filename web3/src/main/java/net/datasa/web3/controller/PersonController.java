package net.datasa.web3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web3.domain.dto.PersonDto;
import net.datasa.web3.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
	
	private final PersonService ps;
	
	@GetMapping("/save-test")
	public String test() {
		
		ps.test();
		
		return "redirect:/";
	}
	
	@GetMapping("/insert-page")
	public String insert() {
		
		return "/person/1. insert-form";
	}
	
	@PostMapping("/insert-person")
	public	String insertPerson(
			@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("age") Integer age
			) {
		PersonDto personDto = new PersonDto();
		personDto.setId(id);
		personDto.setName(name);
		personDto.setAge(age);
		ps.save(personDto);
		
		return "redirect:/";
	}
}
