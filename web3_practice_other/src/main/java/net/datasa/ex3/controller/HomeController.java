package net.datasa.ex3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.ex3.domain.dto.StudentDTO;
import net.datasa.ex3.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * HomeController
 * 요청을 받고 응답을 처리하는 요청 담당 계층
 * Main 페이지 전용 요청 담당
 */
@Controller
@Slf4j
@RequiredArgsConstructor		// final로 선언된 멤버변수를 매개변수로 받는 생성자를 자동생성.
public class HomeController {

	// HomeController에서 StudentService를 사용하기 위해, 생성자 기반 의존성 주입(Dependency Injection)
	private final StudentService ss;
	
	// 메인페이지 / 학생목록을 출력
	@GetMapping({"", "/"})
	public String home(Model model) {
		
		// 다수의 학생정보를 StudentService로부터 가져오기
		List<StudentDTO> studentList = ss.selectAll();
		
		// 로그 출력
		for (StudentDTO student : studentList) {
			log.debug("> 학생: {}", student);
		}
		
		// home.html에서 출력하기위해 Model 객체에 저장
		model.addAttribute("studentList", studentList);
		
		// 리턴 문자열에 .html을 붙여 src/main/resources 의 templates 패키지 로부터 찾음
		return "home";
	}
}
