package net.datasa.web2.controller;

import lombok.extern.slf4j.Slf4j;
import net.datasa.web2.domain.CalcDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@GetMapping("/info")
	public String info() {
		
		return "exView/ex2-infoInput.html";
	}
	
	@PostMapping("/info")
	public String infoOutput() {
		
		
		return "exView/ex2-infoOutput";
	}
}
