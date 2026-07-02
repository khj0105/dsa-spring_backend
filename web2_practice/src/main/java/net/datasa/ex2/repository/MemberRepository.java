package net.datasa.ex2.repository;

import lombok.Getter;
import net.datasa.ex2.dto.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
	[ Repository (레포지토리 / 데이터 접근 계층) ]
	1. 역할: 데이터 저장소(Database, Memory List 등)에 직접 접근하여 데이터의 CRUD를 전담하는 계층.
	2. 주요 기능:
		- 데이터 저장(Create), 조회(Read), 수정(Update), 삭제(Delete) 작업 수행
		- 영속성(Persistence) 컨텍스트 관리 및 DB 쿼리(SQL/JPQL) 실행
	3. 특징:
		- 비즈니스 로직을 모른 채, 오직 "데이터를 어떻게 안전하게 넣고 뺄 것인가"에만 집중함.
	4. 구현 방식에 따른 차이:
		- [일반 클래스 방식 (현재 연습 단계)]: 반드시 클래스 위에 @Repository를 붙여야 스프링 빈으로 등록됨.
		- [Spring Data JPA 방식]: 인터페이스로 선언 후 JpaRepository를 상속받으면,
 		  						 스프링이 구현 클래스를 자동으로 생성해 사용.
 */
@Getter
@Repository
public class MemberRepository {
	
	// DB 대신 데이터를 저장할 List
	private List<Member> memberList = new ArrayList<>();
	
}
