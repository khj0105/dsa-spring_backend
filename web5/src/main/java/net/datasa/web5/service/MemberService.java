package net.datasa.web5.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5.domain.dto.MemberDTO;
import net.datasa.web5.domain.entity.MemberEntity;
import net.datasa.web5.exception.PasswordException;
import net.datasa.web5.repository.MemberRepository;
import net.datasa.web5.security.AuthenticatedUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
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
	
	// 인증 정보
	private final AuthenticatedUserDetailsService uds;
	
	// ----------------------------------------------------------------------------
	/*
		가입시 아이디 중복 확인
		@param searchId 조회할 아이디
		@return 해당 아이디로 가입 가능 여부
	 */
	public boolean idCheck(String searchId) {
		return !mr.existsById(searchId);
	}
	
	// ----------------------------------------------------------------------------
	/*
		회원 가입 처리
		@param member
	 */
	
	public void join(MemberDTO dto) {
		MemberEntity entity = MemberEntity.builder()
				.memberId(dto.getMemberId())
				.memberPassword(
						passwordEncoder.encode(dto.getMemberPassword())
						)
				.memberName(dto.getMemberName())
				.email(dto.getEmail())
				.phone(dto.getPhone())
				.address(dto.getAddress())
//				.enabled(true)
//				.rolename("ROLE_USER")
				.build();
		
		mr.save(entity);
	}
	
	// ----------------------------------------------------------------------------------
	/*
		휴면 계정 해제
		@param memberId
		@param memberPassword
	 */
	public void inactive(String memberId, String memberPassword) {
		MemberEntity entity = mr.findById(memberId)
				.orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));
		
		// 비밀번호 체크
		if (!passwordEncoder.matches(memberPassword, entity.getMemberPassword())) {
			throw new PasswordException("비밀번호가 일치하지 않습니다.");
		}
		
		entity.setEnabled(true);
	}
	
	// ----------------------------------------------------------------------------------------
	/*
		휴면 해제 후 로그인 (수동으로 인증정보를 등록)
		@param memberId
		@param request
	 */
	public void bypassLogin(String memberId, HttpServletRequest request) {
		
		// 1. DB로부터 회원정보를 조회해 인증정보 객체(UserDetails) 가져오기
		UserDetails userDetails = uds.loadUserByUsername(memberId);
		
		// 2. Spring Security가 이해할 수 있는 인증 객체 생성
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				userDetails,					// 실제 유저 정보
				userDetails.getPassword(),
				userDetails.getAuthorities()
		);
		
		// 3. SecurityContext 생성 후 주입
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(auth);		// 인증 객체
		
		// 4. '현재 요청을 처리 중인 스레드'의 보관함에 SecurityContext를 저장
		SecurityContextHolder.setContext(context);
		
		// 5. 세션에도 SecurityContext 저장
		HttpSession session = request.getSession(true);
		// SecurityContext를 스프링 시큐리티가 알아볼 수 있는 이름으로 저장
		session.setAttribute(
				HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context
		);
		
		Authentication info = SecurityContextHolder.getContext().getAuthentication();
		log.debug(">>> 직접 만든 인증객체 정보: {}", info);
	}
}
