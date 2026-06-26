package net.datasa.web2.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web2.domain.CalcDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/*
	연습 문제
 */
@Controller
@Slf4j
@RequestMapping("/ex")
public class Ex7_ExampleController {
	
	/**
	 [연습문제 1]
	 1. home에서 ex/calc 경로로 요청
	 2. 입력 폼을 출력 (입력란 2개, Select 1개, submit버튼 1개 포함)
	 3. 숫자 2개를 입력하고 연산자를 선택 후 submit 버튼 클릭
	 4. 숫자가 아닌 값을 입력하면 JavaScript로 확인하고 오류메시지 출력
	 5. 숫자 2개를 정상적으로 입력하면 서버로 전송
	 6. 콘트롤러에서 값을 전달받아 계산
	 7, 계산한 결과를 Model에 저장하고 View로 이동
	 8. 화면에 계산한 결과 출력
	 
	 파일명:
	 Ex7_ExampleController, ex1-ex1-calcForm.html, ex1-calcResult.html
	 */

	// 계산기 폼 화면으로 이동
	@GetMapping("/calc")
	public String calc() {
		
		return "exView/ex1-calcForm";
	}
	
	// 연산 처리 및 결과화면 이동
	@PostMapping("/calc")
	public String calcOutput(CalcDTO dto, Model model) {
		
		log.debug("calcDTO: {}", dto);
		
		int res = 0, n1, n2;
		
		try {
			switch (dto.getOp()) {
				case "+": res = dto.getNum1() + dto.getNum2(); break;
				case "-": res = dto.getNum1() - dto.getNum2(); break;
				case "*": res = dto.getNum1() * dto.getNum2(); break;
				case "/": res = dto.getNum1() / dto.getNum2(); break;
				default:
					throw new RuntimeException("연산자 오류");
			}
			model.addAttribute("calc", dto);
			model.addAttribute("res", res);
		} catch (Exception e) {
			log.debug("[예외 발생] 원인: {}", e.getMessage());
			return "exView/ex1-calcForm";
		}
		
		return "exView/ex1-calcResult";
	}
	
	// -------------------------------------------------------------------------------
	/*
	[연습문제 2]
	개인정보 입력폼에서 입력한 이름과 주민등록번호를 전달받아,
	처리 결과를 2. ex2-infoOutput.html에서 출력
	
	파일명:
	ex2-infoInput.html, ex2-infoOutput.html
	*/
	
	// 개인정보 입력폼으로 이동
	@GetMapping("/info")
	public String info() {
		
		return "exView/ex2-infoInput";
	}
	
	// 입력값 처리
	@PostMapping("/info")
	public String info(
			@RequestParam("name") String name,
			@RequestParam("ssn") String ssn,
			Model model
	) {
		log.debug("전달된 값: {}, {}", name, ssn);
		
		model.addAttribute("name", name);
		model.addAttribute("ssn", ssn);
		
		// 주민등록번호 처리
		try {
			String ssnYear = ssn.substring(0, 2);
			int month = Integer.parseInt(ssn.substring(2,4));
			int day = Integer.parseInt(ssn.substring(4,6));
			char genderCode = ssn.charAt(7);
			
			// 성별 판별
			String gender = (genderCode == '1' || genderCode =='3') ? "남자" : "여자";
		
			// 출생 연도
			int yearPrefix = (genderCode == '1' || genderCode == '2') ? 1900 : 2000;
		
			int year = yearPrefix + Integer.parseInt(ssnYear);
			
			// 현재 나이 계산
			int thisYear = LocalDate.now().getYear();
			int age = thisYear - year;
			
			// 출생 연도 포맷
			String birth = String.format("""
						%d년 %d월 %d일
					""", year, month, day);
			
			// Model 저장
			model.addAttribute("age", age);
			model.addAttribute("gender", gender);
			model.addAttribute("birth", birth);
			
			return "exView/ex2-infoOutput";
		} catch (Exception e) {
			log.debug("에러발생: {}", e.getMessage());
			model.addAttribute("error", e.getMessage());
			return "exView/ex2-infoInput";
		}
		
		
	}
	
	//-------------------------------------------------------------------
	/*
		[연습문제 3]
		방문횟수 카운트 예제
		방문횟수가 저장된 쿠키를 읽어온다
		없으면 방문횟수는 현재 0으로 처리
		있으면 쿠키에 저장된 숫자가 기존 방문횟수
		방문횟수에 1을 더한다
		쿠키에 증가된 방문횟수를 저장하여 클라이언트로 보낸다
		방문횟수를 Model에 저장하여 ex3-count.html페이지에서 문구 출력
	*/
	@GetMapping("/count")
	public String count(
			@CookieValue(name = "count", defaultValue = "0") int count,
			HttpServletResponse response,
			Model model
	) {
		count++;
		model.addAttribute("count", count);
		
		Cookie cookie = new Cookie("count", Integer.toString(count));
		cookie.setMaxAge(60*60*24*3);
		cookie.setPath("/ex/count");
		response.addCookie(cookie);
		
		
		return "exView/ex3-count";
	}
}
