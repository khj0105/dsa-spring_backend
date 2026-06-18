package net.datasa.web2.controller;

import lombok.extern.slf4j.Slf4j;
import net.datasa.web2.domain.Person;
import net.datasa.web2.domain.PersonForm;
import net.datasa.web2.domain.PersonForm_Messages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/*
	Parameter Test
 */
@RequestMapping("/param")		// 클래스 전체에 공통 URL을 붙여줌
@Slf4j
@Controller
public class Ex2_ParamController {
	
	/*
		@RequestParam 이란?
		HTTP 요청 URL에 있는 파라미터(쿼리스트링)를 자바 변수로 받아오는 어노테이션
		http://localhost:9992/param/input1?id=aaa&name=bbb
		
		key			value
		------------------
		id			aaa
		name		bbb
		이 두 값을 컨트롤러 메서드의 매개변수로 자동 매핑해줌.
		
		* @RequestParam의 속성
		속성명			기능
		name			요청 파라미터의 메서드의 파라미터를 매핑
		defaultValue	요청 파라미터로부터 value값이 없으면 기본값을 매핑
		required		요청 파라미터로부터 해당 key값이 없으면 400 에러 발생
	*/
	
	// 1. get-basic.html 로 페이지 이동
	@GetMapping("/get-param")
	public String show_getBasic() {
		return "/paramView/1. get-basic";
	}
	// http://localhost:9992/input1
	// 				?id=aaa&password=123&name=%ED%99%8D%EA%B8%B8%EB%8F%99&phone=010-3333-4444&com=kt
	// 1. get-basic.html 에서 입력한 값들을 받기
	@GetMapping("/input1")
	public String input1(
			@RequestParam(name = "id", defaultValue = "default") String id
			, @RequestParam(name = "password") String password
			, @RequestParam(name = "name", required = true) String name
			, @RequestParam(name = "phone") String phone
			, @RequestParam(name = "com") String com
	) {
		log.debug("전달된 값> ID:{}, 비밀번호:{}, 이름:{}, 전화번호:{}, 통신사:{}"
				,id, password, name, phone, com);
		
//		return "home";
		return "redirect:/";	// "/ 경로로 재요청 해라"라는 명령어
	}
	
	
	// ---------------------------------------------------------------------
	// 2. post-basic.html 로 이동
	@GetMapping("/post-param")
	public String show_postBasic() {
		return "/paramView/2. post-basic";
	}
	
	@PostMapping("/input2")
	public String input2(
			@RequestParam(name = "id", defaultValue = "default") String id
			, @RequestParam(name = "password") String password
			, @RequestParam(name = "name", required = true) String name
			, @RequestParam(name = "phone") String phone
			, @RequestParam(name = "com") String com
	) {
		log.debug("전달된 값> ID:{}, 비밀번호:{}, 이름:{}, 전화번호:{}, 통신사:{}"
				,id, password, name, phone, com);
		
//		return "home";
		return "redirect:/";
	}
	
	//-------------------------------------------------------------------------
	@GetMapping("/post-param-object")
	public String show_postObject() {
		return "/paramView/3. post-object";
	}
	
	@PostMapping("/input3")
	public String input3(
			@ModelAttribute Person p
	) {
		/*
			@ModelAttribut : 스프링의 "데이터 바인딩" 기능
			스프링이 자동으로 요청 파라미터를 자바의 객체로 매핑해 줌.
		 */
		log.debug("전달된 객체: {}", p);
		
		return "redirect:/";
	}
	
	
	//-------------------------------------------------------------------------
	// queryString이 있다는 건 get방식
	@GetMapping("/input4")
	public String input4(
			@RequestParam(name = "name", defaultValue = "기본값") String name
			, @RequestParam(name = "age", defaultValue = "0") int age
	) {
		log.debug("name: {}, age: {}", name, age);
		return "redirect:/";
	}
	
	
	//--------------------------------------------------------------------------------------------------------
	//import org.springframework.ui.Model;
	@GetMapping("/model")
	public String model(Model model) {
		
		/*
			Model 객체   (서버 데이터를 html에 출력하고 싶음)
			- Controller와 View 사이에 데이터를 전달하기 위해 사용하는 인터페이스
			- 구조: Map<String, Object> 형태의 키-값(Key-Value) 저장소
			- 사용:
				Key: 뷰(HTML)에서 데이터를 꺼낼 때 사용할 이름(식별자)
				Value: 전달할 실제 데이터 객체
			- 유효 범위: '요청(Request) 단위', (즉, 일회용)
				컨트롤러에서 뷰로 데이터가 전달된 직후, 브라우저의 응답이 완료되면
				즉시 소멸, 재요청에는 저장된 데이터가 사라짐.
		 */
		
		String str = "str변수의 문자열";
		int num = 100;
		Person person = new Person("abc", "123", "김철수", "010-1111-2222", "KT");
		
		model.addAttribute("str", str);
		model.addAttribute("num", num);
		model.addAttribute("person", person);
		
		return "paramView/4. model-result";
	}
	
	//--------------------------------------------------------------------------------------------------------
	// validation
	@GetMapping("/validation")
	public String validation(Model model) {
		model.addAttribute("person", new PersonForm());
		return "/paramView/5. validation";
	}
	
	// 유효성 검사
	@PostMapping("/validation")
	public String validation (
//			@Validated @ModelAttribute("person") PersonForm personForm // @Validated는 검사
			@Validated @ModelAttribute("person")
			PersonForm_Messages personForm
			, BindingResult result		// 반드시 @Validated 보다 뒤에 써야함
	) {
		log.debug("validation log personForm: {}", personForm);
		log.debug("validation log result: {}", result);
		
		/*
			필드 에러 vs 글로벌 에러
				@NotNull, @Size ... 등 어노테이션 기반 에러 -> 필드 에러
				result,reject(...) 처럼 특정 필드가 아닌 전체 폼 수준에서 발생되는 에러 -> 글로벌 에러
		 */
		
		// 1. 필드 에러 체크 - 어노테이션 기반 검증
		if (result.hasErrors()) {
			log.debug("[필드 에러] 유효하지 않은 데이터!");
			return "paramView/5. validation";
		}
		
		// 2. 글로벌 에러 체크 - 커스텀한 로직으로 추가적인 유호성 검사
		// 이미 가입된 회원이 있는 경우
		boolean isDuplicate = true;
		if (isDuplicate) {
			// reject( 에러 코드, 에러 메시지 )
//			result.reject("DuplicateUserError", "이미 동일한 정보로 가입된 회원이 존재합니다.");
			result.reject("DuplicateUserError");
			
			log.debug("[글로벌 에러] 중복된 회원 가입 시도!");
			return "paramView/5. validation";
		}
		
		// 3. 모든 검증 통과시 처리
//		Person p = PersonForm.toPerson(personForm);
		Person p = PersonForm_Messages.toPerson(personForm);
		log.debug(">person: {}", p);
		
		return "redirect:/";
	}
	
	
}
