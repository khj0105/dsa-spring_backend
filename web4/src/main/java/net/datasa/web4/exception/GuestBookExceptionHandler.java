package net.datasa.web4.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web4.controller.GuestbookController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
/**
 @ControllerAdvice: 전역 예외 처리기 (Global Exception Handler)
 ========================================================
 1. 개념: 여러 컨트롤러에서 발생하는 예외(Exception)를 한곳에서 모아 처리하는 클래스.
 2. 장점:
 - 각 컨트롤러마다 try-catch를 지저분하게 작성할 필요가 없음 (중복 제거).
 - 에러 발생 시 사용자에게 일관된 안내 페이지나 메시지를 보여줄 수 있음.
 3. assignableTypes 속성:
 - 모든 컨트롤러가 아닌, 특정 컨트롤러(여기서는 GuestbookController)에서
 발생한 예외만 타겟팅하여 처리하도록 제한함.
 */
@ControllerAdvice(assignableTypes = {GuestbookController.class})
public class GuestBookExceptionHandler {
	
	/*
		핸들러(Handler): 요청을 처리하는 모든 객체(컨트롤러도 핸들러의 일종)
			어떤 사건(Event)이나 상황(Exception)이 발생했을 때, 이를 전달받아 ‘처리(Handle)’하는 전담 코드/객체.
     */
	
	// [핸들러 1] 요청한 방명록 글이 존재하지 않는 경우 처리
	@ExceptionHandler(EntityNotFoundException.class)
	public String handleNotFound(EntityNotFoundException e, Model model) {
		log.debug("> EntityNotFoundException");
		model.addAttribute("message", e.getMessage());
		return "error/custom-error-page"; 	// 공통 에러 페이지
	}
	
	// [핸들러 2] 방명록 수정/삭제 시 비밀번호가 틀린 경우 처리
	@ExceptionHandler(PasswordException.class)
	public String handlePassword(PasswordException e, Model model) {
		log.debug("> PasswordException");
		model.addAttribute("message", e.getMessage());
		return "error/custom-error-page"; 	// 공통 에러 페이지
	}
	
	// [핸들러 3] 이미 추천한 글에 다시 추천을 누른 경우(중복 추천) 처리
	@ExceptionHandler(RecommendException.class)
	public String handleRecommend(RecommendException e, Model model) {
		log.debug("> RecommendException");
		model.addAttribute("message", e.getMessage());
		return "error/custom-error-page"; 	// 공통 에러 페이지
	}
}