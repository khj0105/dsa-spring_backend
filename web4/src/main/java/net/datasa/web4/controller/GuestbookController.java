package net.datasa.web4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web4.domain.dto.GuestbookDTO;
import net.datasa.web4.service.GuestbookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/guestbook")
public class GuestbookController {
	
	private final GuestbookService gs; // final의 생성자 주입시켜주는 것이 @RequiredArgsConstructor
	
	
	// --------------------------------------------------------------------------------------
	/*
		글 쓰기 폼으로 이동
		@return writeForm.html
	 */
	@GetMapping("/write")
	public String write() {
		return "/guestbook/writeForm";
	}
	
	/*
		글 쓰기 폼에서 전달된 입력값 저장
		@param dto	게시글 정보(이름, 비밀번호, 내용)
		@return
	 */
	@PostMapping("/write")
	public String write(GuestbookDTO dto) {
		log.debug("입력값: {}", dto);
		gs.write(dto);
		return "redirect:/guestbook/list";
	}
	
	// ----------------------------------------------------------------
	/*
		글 목록 보기
		@param model (출력 내용을 HTML로 전달할 객체)
		@return list.html
	 */
	@GetMapping("/list")
	public String list(Model model) {
		
		List<GuestbookDTO> dtoList = gs.getList();
		model.addAttribute("guestbookList", dtoList);
		
		return "/guestbook/list";
	}
	
	// --------------------------------------------------------------------
	/*
		글 삭제
		@param num
		@param password
		@return /guestbook/list
	 */
	@PostMapping("/delete/{num}")
	public String delete(
			@PathVariable("num") Integer num,
			@RequestParam("password") String password
	) {
		log.debug("게시글번호: {}, 비밀번호: {}", num, password);
		gs.delete(num, password);
		
		return "redirect:/guestbook/list";
	}
}
