package net.datasa.web5.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
	회원 서비스
 */
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberService {
	
	// 회원 관련 Repository
	private final MemberRepository mr;
	
	// 암호화 객체
	private final BCryptPasswordEncoder passwordEncoder;
	
	// ----------------------------------------------------------------------------
	/*
		가입시 아이디 중복 확인
		@param searchId 조회할 아이디
		@return 해당 아이디로 가입 가능 여부
	 */
	public boolean idCheck(String searchId) {
		return !mr.existsById(searchId);
	}
}
