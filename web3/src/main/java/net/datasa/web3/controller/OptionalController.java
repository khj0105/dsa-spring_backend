package net.datasa.web3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Controller
@Slf4j
public class OptionalController {
	
	/*
		* Optional<T> :
		null 값으로 인한 NullPointerException을 방지하기 위한 클래스
		"값이 있을 수도, 없을 수도 있음"을 명시적으로 표현해주는 컨테이너 클래스
			- null을 직접 사용하는 것보다 안정적이고 가독성이 높음.
			- 코드가 간결하고, NPE를 줄이며, 함수형 스타일에 적합.
	 */
	@GetMapping("/optional")
	public String optional() {
		log.debug("--- [Optional 테스트] ---");
		// 가상의 데이터 (DB 대용)
		Map<String, String> db = new HashMap<>();
		db.put("hong", "길동");
		
		// 패턴 1. 값이 확실히 있을 때만 안전하게 꺼내 스기: ifPresent()
		Optional<String> res1 = Optional.ofNullable(db.get("hong"));
		Optional<String> res2 = Optional.ofNullable(db.get("Lee"));
		
		if (res1.isPresent()) {		// 데이터가 존재한다면
			String name = res1.get();
			log.debug("패턴1 (데이터 있음) > 이름: {}", name);
		}
		// res2.ifPresent(name2 -> log.debug("패턴1 (데이터 없음) > 이름: {}", name2));
		res2.ifPresent(new Consumer<String>() { // 익명객체
			@Override
			public void accept(String s) {
				log.debug("패턴1 (데이터 없음) > 이름: {}", s);
			}
		});
		/*
			* 람다표현식 *
			장점 1. 코드 간결성
				2. 함수형 프로그래밍
				3. 컬렉션, 스트림API 와 사용시 용이
				
			int max(int a, int b) {
				return a > b ? a : b;
			}
			> 1. 메서드 이름과 반환 타입 삭제
			
			(int a, int b) {
				return a > b ? a : b;
			}
			> 2. 파라미터와 본문 사이에 화살표 삽입
			
			(int a, int b) -> { return a > b ? a : b};
			> 3. 컴파일러가 타입을 추론할 수 있다면 파라미터 타입도 삭제
			
			(a, b) -> a > b ? a : b;
			
			* 주의 *
				- 파라미터가 한개면 () 생략 가능
				ex. (name) -> log.debug(name)
					name -> log.debug(name)
				- 파라미터가 없으면 빈 괄호 필수 작성
				ex. () -> new RuntimeException()
				- 실행문이 딱 1줄인 경우, 중괄호 {} 와 return 생략 가능
		 */
		log.debug("--- 람다 ---");
		Calculator cal1 = new Calculator() {
			@Override
			public int add(int a, int b) {
				return a + b;
			}
		};
		log.debug("cal1: {}", cal1.add(5, 10));
		Calculator cal2 = (int a, int b) -> {
			return a + b;
		};
		log.debug("cal2: {}", cal2.add(10, 20));
		Calculator cal3 = (a, b) -> a + b;
		log.debug("cal3: {}", cal3.add(20, 30));
		
		// 패턴 2. 데이터가 없을 때 기본값 지정: orElse()
		Optional<String> res3 = Optional.ofNullable(db.get("hong"));
		Optional<String> res4 = Optional.ofNullable(db.get("kim"));
		String name3 = res3.orElse("알 수 없는 사용자");
		log.debug("패턴2 (데이터 있음) > 결과: {}", name3);
		String name4 = res4.orElse("알 수 없는 사용자");
		log.debug("패턴2 (데이터 없음) > 결과: {}", name4);
		
		// 패턴 3. 데이터가 없으면 에러(예외) 던지기: orElseThrow() -> JPA 핵심
		try {
			Optional<String> res5 = Optional.ofNullable(db.get("lee"));
			
			String name5 = res5.orElseThrow(() -> new RuntimeException("해당 ID의 회원이 없습니다."));
			log.debug("패턴 3 결과: {}", name5);
		} catch (Exception e) {
			log.error("패턴3 (예외 포착) > 에러 메시지: {}", e.getMessage());
		}
		
		return "redirect:/";
	}
}

// 람다식으로 변환이 가능한 '함수형 인터페이스'임을 선언하는 어노테이션
@FunctionalInterface
interface Calculator {
	int add(int a, int b);
//	int minus(int a, int b);
}