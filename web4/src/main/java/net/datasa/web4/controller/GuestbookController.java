package net.datasa.web4.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web4.domain.dto.GuestbookDTO;
import net.datasa.web4.service.GuestbookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	
	// -----------------------------------------------------------------
	/*
		글 수정 페이지 이동
		@param num
		@param password
		@param model
		@return updateForm.html
	 */
	@GetMapping("/update/{num}")
	public String update(
			@PathVariable("num") Integer num,
			@RequestParam("password") String password,
			Model model /* 서버에서 사용자 측으로*/
	) {
		log.debug("수정할 게시글 번호: {], 비밀번호: {}", num, password);
		
		GuestbookDTO  dto = gs.selectGuestbook(num, password);
		model.addAttribute("guestbook", dto);
		
		return "/guestbook/updateForm";
	}
	
	// -----------------------------------------------------------------
	/*
		글 수정 처리
		@param num
		@param dto
		@return /guestbook/list
	 */
	@PostMapping("/update/{num}")
	public String update(
			@PathVariable("num") Integer num,
			GuestbookDTO dto
	){
		log.debug("수정할 글 번호: {}", num);
		log.debug("수정할 글 정보: {}", dto);
		dto.setNum(num);
		
		gs.update(dto);
		
		return "redirect:/guestbook/list";
	}
	
	// -----------------------------------------------------------------
	/*
		추천 처리
		@param num				추천하고자하는 글 번호
		@param request			IP를 추출하기 위한 요청객체
		@param ra				redirect를 하더라도 서버측 데이터를 저장될 수 있는 객체
		@return /guestbook/list
	 */
	@PostMapping("/recommend/{num}")
	public String recommend(
			@PathVariable("num") Integer num,
			HttpServletRequest request,
			RedirectAttributes ra
	) {
		log.debug("접속자 IP: {}", request.getRemoteAddr());
		log.debug("접속자 브라우저 정보: {}", request.getHeader("USER-Agent"));
		log.debug("접속자 요청 URL: {}", request.getRequestURL());
		
		// IP
		String clientIp = request.getRemoteAddr();
		
		// 추천 처리
		gs.recommend(num, clientIp);
		ra.addFlashAttribute("msg", "해당 글이 추천되었습니다.");
		
		return "redirect:/guestbook/list";
	}
}
