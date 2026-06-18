package net.datasa.web2.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
	세션 테스트
 */
@Controller
@Slf4j
@RequestMapping("/session")
public class Ex3_SessionController {
	/*
		[ 세션 ]
		서버 측에서 사용자의 상태를 유지하는 메커니즘
		서버는 각 클라이언트에 대해 고유한 세션 ID를 생성하고,
		클라이언트가 해당 세션 ID를 통해 서버의 상태를 유지.
		- 저장 위치: 서버 측(서버 메모리나 DB 등)에 저장
		- 유효 기간: 세션에는 만료 시간이 있으며, 일정 기간 활동이 없으면 세션 만료
		- 보안: 서버 측에 저장되므로 비교적 안전
		- 범위: 서버 내에서만 유효
	 */
	
	// 세션에 값 저장
	@GetMapping("/save")
	public String session1(HttpSession session) {
		
		// key-value
		session.setAttribute("name", "홍길동");
		
		return "redirect:/";
	}
	
	// 세션 값 읽기
	@GetMapping("/read")
	public String session2(HttpSession session) {
		String name = (String) session.getAttribute("name");
		log.debug("=== 세션 읽기 ===");
		log.debug("> 세션명: {}", name);
		
		return "redirect:/";
	}
	
	// 세션 삭제
	@GetMapping("/clear")
	public String session3(HttpSession session) {
		
		// 특정 key값에 매핑된 데이터 정보를 삭제함.
		session.removeAttribute("name");
		
		// 세션 데이터 전체 삭제
		session.invalidate();
		
		return "redirect:/";
	}
	
	// ---------------------------------------------------------------------
	// 로그인
	@GetMapping("/login")
	public String login() {
		return "sessionView/loginForm";
	}
	
	/*
		HttpSession 객체
		사용자별로 서버가 관리하는 저장소
		저장위치		서버(메모리)
		구분 기준		클라이언트가 보내는 JSESSIONID 쿠키(세션 쿠키)
		기본 만료		시간 30분 (설정 가능)
		사용 방식		setAttribute(), getAttribute()로 저장/조회
					removeAttribute(), invalidate()로 삭제(개별/전체)
	 */
	
	// 로그인 처리
	@PostMapping("/login")
	public String login(
			@RequestParam("id") String id
			, @RequestParam("pw") String pw
			, HttpSession session
	) {
		log.debug("> 서버로 전달된 입력값 / id: {}, pw: {}", id, pw);
		
		// ID가 "abc"이고 비밀번호가 "123"인 경우 로그인 처리
		if (id.equals("abc") && pw.equals("123")) {
			session.setAttribute("loginId", id);
			log.debug("{} 님 로그인", id);
			return "redirect:/";
		}
		// 불일치할 경우, 로그 출력 후 로그인 페이지
		else {
			log.debug("로그인 실패...");
			return "sessionView/loginForm";
		}
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginId");
		session.invalidate();
		return "redirect:/";
	}
	
	// 로그인해야 볼 수 있는 페이지 이동
	@GetMapping("/loginTest")
	public String loginTest(HttpSession session) {
		
		String id = (String) session.getAttribute("loginId");
		
		if (id == null || !id.equals("abc")) {
			return "redirect:/session/login";
		} else {
			return "sessionView/loginResult";
		}
	}
}
