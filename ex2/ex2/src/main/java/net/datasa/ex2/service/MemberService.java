package net.datasa.ex2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.ex2.dto.Member;
import net.datasa.ex2.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
	[ Service (서비스) ]
	1. 역할: 애플리케이션의 핵심 비즈니스 로직 및 규칙을 전담하여 처리하는 계층.
	2. 주요 기능:
		- 데이터 가공, 연산, 유효성 검사, 조건문 처리 등 핵심 도메인 로직 수행
		- 데이터베이스 접근(Repository/DAO)을 통한 데이터 CRUD 제어
		- 트랜잭션(@Transactional) 단위 설정 및 관리
	3. 특징: 웹 환경(HTTP 요청, URL 구조, 세션 등)에 의존하지 않으며, 순수한 자바 코드로 비즈니스 요구사항을 완성하는 데만 집중.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository mr;

	// 회원가입
	public boolean save(Member member) {
		// Repository 로부터 회원목록 가져오기
		List<Member> memberList = mr.getMemberList();
		
		// 아이디가 같으면 가입 실패
		for (Member m : memberList) {
			if (m.getId().equals(member.getId())) {
				return false;
			}
		}

		memberList.add(member);
		return true;
	}

	// 회원확인
	public boolean loginCheck(String id, String pw) {
		List<Member> memberList = mr.getMemberList();
		
		for (Member m : memberList) {
	        if (m.getId().equals(id) && m.getPw().equals(pw)) {
	            return true;
	        }
	    }
	    return false;
	}

	// 회원목록 조회
	public List<Member> selectList() {
		List<Member> memberList = mr.getMemberList();
		
		return memberList;
	}
		
}
