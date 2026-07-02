package net.datasa.web3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web3.domain.dto.PersonDto;
import net.datasa.web3.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
	
	// -----------------------------------------------------------------------------------------
	// 입력 폼으로 이동
	@GetMapping("/insert-page")
	public String insert() {
		
		return "/person/1. insert-form";
	}
	
	// 저장 처리
	@PostMapping("/insert-person")
	/*public	String insertPerson(
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
	}*/
	public String insert(@ModelAttribute PersonDto dto) {
		log.debug("전달된 데이터: {}", dto);
		
		try {
			ps.insert(dto);
			log.debug("[저장 성공]");
		} catch (Exception e) {
			log.debug("[저장 실패] {}", e.getMessage());
		}
		return "redirect:/";
	}
	
	// -----------------------------------------------------------------------------------------
	// 조회 폼으로 이동
	@GetMapping("/select-page")
	public String select() {
		return "/person/2. select-form";
	}
	
	// 조회 처리
	@GetMapping("/select-person")
	public String select (
			@RequestParam("searchId") String searchId,
			Model model
	) {
		log.debug("조회할 아이디: {}", searchId);
		PersonDto dto = null;
		
		try {
			dto = ps.select(searchId);
			log.debug("[조회 성공] {}", dto);
		} catch (Exception e) {
			log.debug("[조회 실패] {}", e.getMessage());
		}
		
		model.addAttribute("searchId", searchId);
		model.addAttribute("person", dto);
		
		return "/person/3. select-result";
	}
	
	
	// ----------------------------------------------------------------------
	// 삭제 폼으로 이동 > person/4. delete-form
	@GetMapping("/delete-page")
	public String delete() {
		return "/person/4. delete-form";
	}
	
	// 삭제 처리
	// html 로부터 id값 받아오기
	// id를 서비스에게 넘겨주면서 삭제처리
	// 삭제처리 결과에 따라 로그 출력
	// 삭제하려던 id와 삭제 결과를 model에 저장
	// person/5. delete-result
	@PostMapping("/delete-person")
	public String delete (
			@RequestParam("deleteId") String deleteId,
			Model model
	) {
		log.debug("삭제할 아이디: {}", deleteId);
		
		boolean result = true;
		
		try {
			ps.delete(deleteId);
			log.debug("[삭제 성공]");
		} catch (Exception e) {
			result = false;
			log.debug("[삭제 실패] {}", e.getMessage());
		} finally {
			model.addAttribute("deleted", deleteId);
			model.addAttribute("result", result);
		}
		return "/person/5. delete-result";
	}
	
	// -----------------------------------------------------------------------
	// 목록 페이지로 이동
	@GetMapping("/list-page")
	public String selectAll(Model model) {
		
		// 회원목록 가져오기 ( .findAll() - null값을 가지지 않음 )
		List <PersonDto> personLsit = ps.selectAll();
		model.addAttribute("personList", personLsit);
		
		return "/person/6. person-list";
	}
	
	// -----------------------------------------------------------------------
	@GetMapping("/info" + "/{searchId}") // a태그면 getmapping
	public String info(
			@PathVariable(name = "searchId", required = false) String searchId,
			Model model
	) {
		
			log.debug("조회할 아이디: {}", searchId);
			PersonDto dto = null;
			
			try {
				dto = ps.select(searchId);
				log.debug("[조회 성공] {}", dto);
			} catch (Exception e) {
				log.debug("[조회 실패] {}", e.getMessage());
			}
		model.addAttribute("searchId", searchId);
		model.addAttribute("person", dto);
		
		return "/person/3. select-result";
	}
}
