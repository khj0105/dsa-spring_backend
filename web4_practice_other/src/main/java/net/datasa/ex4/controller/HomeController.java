package net.datasa.ex4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.ex4.service.ChickenService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

	private final ChickenService cs;
	
	@GetMapping({"", "/"})
	public String home() {
		return "order";
	}
	
	@GetMapping("modal")
	public String modal() {
		return "modal";
	}
}
