package net.datasa.ex4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.ex4.domain.dto.ChickenDTO;
import net.datasa.ex4.service.ChickenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/chicken")
@RequiredArgsConstructor
public class ChickenController {
	
	private final ChickenService cs;
	
	@GetMapping("/order")
	public String orderPage() {
		return "redirect:/";
	}
	
	@PostMapping("/order")
	public String submitOrder(
			@ModelAttribute ChickenDTO dto
			) {
		log.debug("주문: {}", dto);
		
		try {
			cs.order(dto);
			log.debug("주문 성공!");
		} catch (Exception e) {
			log.debug("[예외 발생] 주문 실패..");
		}
		
		return "redirect:/chicken/list";
	}
	
	@GetMapping("/list")
	public String orderList(Model model) {
		
		List<ChickenDTO> orderList = cs.list();
		model.addAttribute("orderList", orderList);
		
		return "order-list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteOrder(
			@PathVariable("id") Integer id
	) {
		try {
			cs.deleteOrder(id);
			log.debug("삭제 성공!");
		} catch (Exception e) {
			log.debug("[예외 발생] 삭제 실패..");
		}
		
		return "redirect:/chicken/list";
	}
}
