package net.datasa.web3_practice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web3_practice.domain.dto.StudentDTO;
import net.datasa.web3_practice.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
	private final StudentService studentService;
	
	// 1. 전체 학생 목록 출력
	@GetMapping("/list")
	public String list(Model model) {
		List<StudentDTO> list = studentService.selectAll();
		model.addAttribute("studentList", list);
		return "home";
	}
	
	// 2. 등록 페이지 폼 이동
	@GetMapping("/enroll")
	public String enrollForm() {
		return "enroll";
	}
	
	// 3. 등록 처리
	@PostMapping("/enroll")
	public String enroll(@ModelAttribute StudentDTO dto) {
		log.debug("등록 요청 데이터: {}", dto);
		try {
			studentService.insert(dto);
		} catch (Exception e) {
			log.error("등록 실패: {}", e.getMessage());
		}
		return "redirect:/student/list";
	}
	
	// 4. 학생 개별 조회 (스크린샷 주소 기반: /student/select?sid=5 )
	@GetMapping("/select")
	public String select(@RequestParam("sid") int sid, Model model) {
		try {
			StudentDTO dto = studentService.selectOne(sid);
			model.addAttribute("student", dto);
		} catch (Exception e) {
			log.error("조회 실패: {}", e.getMessage());
			return "redirect:/student/list";
		}
		return "select";
	}
	
	// 5. 수정 페이지 폼 이동 (PathVariable 방식)
	@GetMapping("/update/{sid}")
	public String updateForm(@PathVariable("sid") int sid, Model model) {
		try {
			StudentDTO dto = studentService.selectOne(sid);
			model.addAttribute("student", dto);
		} catch (Exception e) {
			return "redirect:/student/list";
		}
		return "updateForm";
	}
	
	// 6. 수정 처리
	@PostMapping("/update")
	public String update(@ModelAttribute StudentDTO dto) {
		try {
			studentService.update(dto);
		} catch (Exception e) {
			log.error("수정 실패: {}", e.getMessage());
		}
		return "redirect:/student/select?sid=" + dto.getStudentId();
	}
	
	// 7. 삭제 처리 (PathVariable 방식)
	@GetMapping("/delete/{sid}")
	public String delete(@PathVariable("sid") int sid) {
		try {
			studentService.delete(sid);
		} catch (Exception e) {
			log.error("삭제 실패: {}", e.getMessage());
		}
		return "redirect:/student/list";
	}
}
