package net.datasa.ex3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.ex3.domain.dto.StudentDTO;
import net.datasa.ex3.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller
 * 요청을 받고 응답을 처리하는 요청 담당 계층
 * Student 전용 요청 담당
 */
@Controller
@Slf4j
@RequestMapping("/student")		// StudentController 에 정의된 모든 메서드들의 공통 경로 추가
@RequiredArgsConstructor		// final로 선언된 멤버변수를 매개변수로 받는 생성자를 자동생성.
public class StudentController {

	// StudentController에서 StudentService를 사용하기 위해, 생성자 기반 의존성 주입(Dependency Injection)
	private final StudentService ss;
	
	// **********************************************************************
	// 학생정보 등록 페이지 이동
	@GetMapping("/save-form")
	public String newStudent() {
		return "student/enroll";
	}
	
	
	// **********************************************************************
	// 학생정보 등록 처리 요청
	@PostMapping("/save")
	public String save(StudentDTO student) {
		
		log.debug("> 학생정보 입력값: {}", student);
		
		// StudentService 에 학생정보를 주고 입력받은 학생정보 저장 처리
		try {
			ss.save(student);
			log.debug("> 학생정보 등록 성공!");
		} catch (Exception e) {
			log.debug("> [예외발생] 등록되지 않았습니다.");
		}
		return "redirect:/";
	}
	
	
	// **********************************************************************
	// 학생정보 수정 페이지 이동 (경로값으로부터 학생번호 추출)
	@GetMapping("/update" + "/{uid}")
	public String updateForm(
			@PathVariable("uid") int studentId,
			Model model
	) {
		log.debug("> 학번: {}", studentId);
		// StudentService 에 학생번호를 주고 저장된 학생정보 가져와 Model에 저장
		try{
			StudentDTO student = ss.find(studentId);
			log.debug("> 학생정보: {}", student);
			model.addAttribute("student", student);
			return "student/updateForm";
		} catch (Exception e) {
			log.debug("> 학번: ({}) - 존재하지 않는 학생입니다.", studentId);
		}
		return "redirect:/";
	}
	
	
	// **********************************************************************
	// 학생정보 수정 처리 요청
	@PostMapping("/update" + "/{uid}")
	public String update(
			@PathVariable("uid") int studentId,
			StudentDTO student
	) {
		
		student.setStudentId(studentId);
		log.debug("> 학생정보: {}", student);
		
		// StudentService 에 학생정보를 주고 저장된 학생정보 수정 처리
		try {
			ss.update(student);
			log.debug("> 수정 성공!");
		} catch(Exception e) {
			log.debug("> 수정 실패! - {}", e.getMessage());
		}
		
		return "redirect:/";
	}
	
	
	// **********************************************************************
	// 학생정보 삭제 처리 요청 (경로값으로부터 학생번호 추출)
	@GetMapping("/delete" + "/{did}")
	public String delete(
			@PathVariable("did") int studentId
	) {
		log.debug("> 학번: {}", studentId);
		
		// StudentService 에 학생번호를 주고 저장된 학생정보 삭제 처리
		try {
			boolean result = ss.delete(studentId);
			if (result) log.debug("> 삭제되었습니다.");
			else 		log.debug("> 해당 학생이 존재하지 않습니다.");
		} catch (Exception e) {
			log.debug("> [예외 발생] 삭제되지 않았습니다.");
		}
		return "redirect:/";
	}
	
	
	// **********************************************************************
	// 학생정보 조회 요청 (쿼리스트링으로부터 학생번호 추출)
	@GetMapping("select")
	public String select(@RequestParam("sid") int studentId, Model model) {
		
		log.debug("{> 학번: {}}", studentId);
		
		// StudentService 에 학생번호를 주고 저장된 학생정보 가져와 Model에 저장
		try {
			StudentDTO student = ss.find(studentId);
			log.debug("> 학생정보: {}", student);
			model.addAttribute("student", student);
			return "student/select";
		} catch(Exception e) {
			log.debug("> 학번({}) - 존재하지 않는 학생입니다.", studentId);
			return "redirect:/";
		}
	}
	
}
