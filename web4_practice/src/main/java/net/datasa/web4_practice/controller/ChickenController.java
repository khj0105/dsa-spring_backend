package net.datasa.web4_practice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web4_practice.domain.dto.ChickenDto;
import net.datasa.web4_practice.service.ChickenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChickenController {
	private final ChickenService chickenService;
	
	@GetMapping("/chicken/order")
	public String chickenOrderForm() {
		return "/chicken/order";
	}
	
	@PostMapping("/chicken/order")
	@ResponseBody
	public ChickenDto saveOrder(@RequestBody ChickenDto dto) {
		return chickenService.saveOrder(dto);
	}
	
	@GetMapping("/chicken/list")
	public String chickenList(Model model) {
		List<ChickenDto> orders = chickenService.getOrderList();
		model.addAttribute("orders", orders);
		return "/chicken/list";
	}
	
	@DeleteMapping("/chicken/order/{id}")
	@ResponseBody
	public void deleteOrder(@PathVariable Integer id) {
		chickenService.deleteOrder(id);
	}
}
