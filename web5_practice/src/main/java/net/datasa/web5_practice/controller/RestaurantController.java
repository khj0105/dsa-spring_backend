package net.datasa.web5_practice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5_practice.domain.dto.RestaurantDTO;
import net.datasa.web5_practice.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {
	
	private final RestaurantService rs;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<RestaurantDTO> restaurantList = rs.getRestaurantList();
		model.addAttribute("restaurantList", restaurantList);
		return "home";   // home.html
	}
	
	@GetMapping("/write")
	public String writeForm() {
		return "enrollment";   // enrollment.html
	}
	
	@PostMapping("/write")
	public String write(@ModelAttribute RestaurantDTO dto) {
		log.debug("등록할 맛집 정보: {}", dto);
		rs.write(dto);
		return "redirect:/restaurant/list";
	}
}