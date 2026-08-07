package net.datasa.survey.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.survey.domain.dto.PerfumeDTO;
import net.datasa.survey.service.PerfumeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/*
   Controller
 */
@Controller
@Slf4j
@RequiredArgsConstructor
public class PerfumeController {
	
	private final PerfumeService ps;
	
	@GetMapping({"", "/"})
	public String mainPage() {
		return "main";
	}
	
	@PostMapping("/perfume/add")
	public String add(
			PerfumeDTO dto
	) {
		log.debug("들어온 정보: {}", dto);
		ps.save(dto);
		
		return "redirect:/";
	}
	
	@GetMapping("/perfume/result")
	public String result(
			Model model
	) {
		List<PerfumeDTO> dtoList = ps.selectAll();
		model.addAttribute("perfumeList", dtoList);
		
		return "/result";
	}
}
