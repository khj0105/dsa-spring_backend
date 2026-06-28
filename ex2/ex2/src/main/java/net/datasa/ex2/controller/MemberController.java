package net.datasa.ex2.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.ex2.dto.Member;
import net.datasa.ex2.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
	[ Controller (컨트롤러) ]
	1. 역할: 클라이언트의 요청(Request)을 최초로 접수하고, 최종 결과(Response)를 반환하는 진입점.
	2. 주요 기능:
		- URL 매핑 및 HTTP 메서드(GET, POST 등) 처리
		- 요청 파라미터 수집 및 검증 (@RequestParam, @ModelAttribute, @PathVariable 등..)
		- 적절한 비즈니스 로직(Service) 호출 후 데이터 전달
		- 화면 전송(HTML 뷰 이름 반환) 또는 REST 데이터 응답(JSON 등)
	3. 특징: 비즈니스 로직(연산, 검사 등)을 직접 수행하지 않고, 흐름 제어(라우팅)만 담당.
 */
@Controller
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor // 클래스 안에 있는 final 변수를 자동으로 생성자에 넣어줌
public class MemberController {
	
	/*
		스프링 빈 주입 방법(스프링에서 관리하는 객체)
		1. 필드 주입  		필드에 @Autowired
		2. 생성자 주입 		생성자에 @Autowired			(권장 방식)
		3. setter 주입 		setter를 정의 @Autowired
 	*/
	// MemberService 연결
	
	// 1. 필드 주입
//	@Autowired
//	MemberService ms = new MemberService();
//	MemberService ms;
	
	// 2. 생성자 주입 (권장 방식)
//	MemberService ms
//	@Autowired
//	public MemberController(MemberService ms) {
//		this.ms = ms;
//	}
	
	// 3. setter 주입
//	@Autowired
//	public void setMemberService(MemberService ms) {
//		this.ms = ms;
//	}
	
	
	// 실제 사용되는 생성자 주입 방식
	private final MemberService ms; // 원래 객체를 생성해야 에러 안남 final이라서
	
	// -------------------------------------------------------------------------------------
	/**
	 * 회원가입
	 */
	// 페이지 이동
	@GetMapping("/join")
	public String join() {
		return "member/join";
	}
	// 입력값 받아서 회원가입 처리
	@PostMapping("/join")
	public String joinForm(Member member) {
		
		log.debug("> 회원가입 데이터: {}", member);
		
		boolean result = ms.save(member);
		
		if (result) {
			log.debug("> 회원가입 성공!");
			log.debug("> 회원목록: {}", ms.selectList());
			return "redirect:/";
		} else {
			log.debug("> 이미 존재하는 ID입니다.");
			return "member/join";
		}
	}
	
	
	// -------------------------------------------------------------------------------------
	/**
	 * 로그인, 로그아웃
	 */
	// 로그인 페이지 이동
	@GetMapping("/login")
	public String loginForm() {
		return "member/login";
	}
	// 입력값 받아서 로그인 처리
	@PostMapping("/login")
	public String login(
			@RequestParam(name = "id") String id,
			@RequestParam(name = "pw") String pw,
			HttpSession session) {
		
		log.debug("> 로그인 시도 ID: {}", id);
		boolean isValid = ms.loginCheck(id, pw);
		
		if (isValid) {
			log.debug("> 로그인 성공!");
			session.setAttribute("loginId", id);
			return "redirect:/";
		} else {
			log.debug("> 로그인 실패: 아이디 또는 비밀번호 불일치");
			return "member/login";
		}
	}
	// 로그아웃 처리
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.debug("> 로그아웃 요청 처리");
		session.invalidate();
		return "redirect:/";
	}
	
	
	// -------------------------------------------------------------------------------------
	/**
	 * 회원목록
	 */
	@GetMapping("/list")
	public String memberList(Model model) {
		log.debug("> 전체 회원목록 조회 요청");
		
		List<Member> list = ms.selectList();
		
		model.addAttribute("memberList", list);
		
		return "member/list";
	}
}
