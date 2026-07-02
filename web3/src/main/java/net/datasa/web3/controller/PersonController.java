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
	/*
		* HTTP 요청 파라미터 규칙: @RequestParam vs @PathVariable
			- RESTful API 표준 규칙
				'주소(URL)'를 통해 자원(Data)을,
				'행동(HTTP 메서드 방식)'을 통해 무엇을  할지 의도를 표현
		@RequestParam (자원을 '정렬, 필터링, 검색'할 때 사용 - 조건)
			- ex. GET /member?name=홍길동
				회원(member) 중 name이 홍길동 인 조건으로 조회
			- ex. GET /products?category=shoes&size=270
				신발 카테고리 중 사이즈가 270인 상품을 조회
		@PathVariable (자원의 '정체성(식별자), 특정 리소스 조회, 수정, 삭제'
		 				을 나타낼 때 사용 - 대상의 이름)
			- ex. GET /member/1
				회원(member) 중 id가 1인 사람의 정보 조회
			- ex. GET /products/p001
				상품코드 p001인 상품 조회
			- ex. POST /members/5/orders
				회원 중 id가 5번인 사람의 새로운 주문을 생성
			- ex. PATCH /members/5
				회원 중 id가 5번인 사람 정보의 일부를 수정
			- ex. DELETE /boards/10/comments/3
				10번 게시글에 있는 댓글 중 3번 댓글을 삭제
	 */
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
	
	// -----------------------------------------------------------------------
	// 삭제 처리 (경로로부터 파라미터를 받아 처리)
	@GetMapping("/delete-person" + "/{deleteId}")
	public String deleteUser(
			@PathVariable("deleteId") String deleteId
	) {
		log.debug("삭제할 아이디 {}", deleteId);
		
		try {
			ps.delete(deleteId);
			log.debug("[삭제 성공]");
		} catch (Exception e) {
			log.debug("[삭제 실패] {}", e.getMessage());
		}
		return "redirect:/person/list-page";
//		return "person/6. person-list";
		
	}
	
	// ------------------------------------------------------------
	// 수정 페이지로 이동
	@GetMapping("/update-page" + "/{updateId}")
	public String update(
			@PathVariable("updateId") String updateId,
			Model model
	) {
		log.debug("수정할 아이디: {}", updateId);
		
		PersonDto dto = null;
		try {
			dto = ps.select(updateId);
			log.debug("[조회 성공] {}", dto);
			model.addAttribute("person", dto);
		} catch (Exception e) {
			model.addAttribute("updateId", updateId);
			log.debug("[조회 실패] {}", e.getMessage());
		}
		return "/person/7. update-form";
	}
	
	// 수정 처리
	@PostMapping("/update-person" + "/{updateId}")
	public String update(
			@PathVariable("updateId") String updateId,
			PersonDto dto
	) {
		log.debug("수정할 회원 ID: {}", updateId);
		log.debug("수정할 데이터: {}", dto);
		dto.setId(updateId);
		
		try {
			ps.update(dto);
			log.debug("[수정 성공]");
		} catch (Exception e) {
			log.debug("[수정 실패] {}", e.getMessage());
		}
		return "redirect:/person/info/" + dto.getId();
	}
}
