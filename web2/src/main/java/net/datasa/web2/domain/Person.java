package net.datasa.web2.domain;

/*
	Domain - 비즈니스의 핵심 규칙과 개념을 표현
	
	1. Entity : DB 테이블과 1:1로 매핑되는 "식별자"를 가지는 객체(JPA가 관리)
				식별자(ID)가 중요함.
	2. VO	  : 속성들의 묶음으로 정의되는 "값"
				값이 중요함. (작은 개념을 표현하는 객체, 그 자체로 정체성)
	3. DTO	  : 계층간 데이터를 효율적으로 전달하기 위해 설계된 데이터 묶음
				변경보다 전달이 중요함.
				(Client <-> Controller <-> Service)
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 객체 생성시 빌더 패턴을 자동으로 구현(복잡한 객체를 가독성있게 생성)
@Builder
// @Getter, @Setter, @ToString, @EqualsAndHashCode
@Data
// 기본 생성자를 자동으로 생성
@NoArgsConstructor
// 모든 필드를 매개변수로 가지는 생성자를 자동으로 생성
@AllArgsConstructor
public class Person {
	String id;
	String password;
	String name;
	String phone;
	String com;
}
