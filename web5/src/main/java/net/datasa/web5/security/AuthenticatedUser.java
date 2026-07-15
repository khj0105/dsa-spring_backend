package net.datasa.web5.security;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/*
	회원 인증 정보 객체
	- UserDetail는 스프링 시큐리티가 사용자의 인증 정보를 이해할 수 있도록
	 규격화해 둔 "회원 정보 표준 인터페이스"
	- Spring Security는 로그인 시 UserDetails 객체를 내부적으로 사용하여
	 비밀번호, 권한, 계정 활성 여부 등을 확인 함
 */
@Slf4j
@Builder
@Getter /* 인증 정보는 수정되면 안되기 때문*/
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthenticatedUser implements UserDetails {
	private String id;
	private String password;
	private String name;
	private String roleName;
	private boolean enabled;
	
	// 1. 권한명 리턴
	// 이 사용자가 어떤 권한(ROLE)을 가지고 있는지 스프링 시큐리티에게 알려주는 메서드
	// 사용자에게 부여된 권한 목록(ex. ROLE_USER, ROLE_ADMIN 등)을 반환
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(roleName));
	}
	
	@Override
	public String getUsername() {
		return id;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	/*
		계정 상태 관련
	*/
	// 계정 만료 여부
	//		true: 만료되지 않음 > 로그인 가능
	//		false: 만료됨 > 로그인 차단
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	// 계정 잠금 여부
	//		true: 잠기지 않음 > 로그인 가능
	//		false: 계정이 잠김 > 로그인 차단
	// ex. 로그인 실패 5회
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	// 비밀번호 만료 여부
	//		true: 만료되지 않음 > 로그인 가능
	//		false: 비밀번호가 만료됨 > 로그인 차단
	// ex. 비밀번호 변경 후 90일 지나면 false로 변경
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	// 계정 활성화 여부
	@Override
	public boolean isEnabled() {
		log.debug("계정 상태: {}", this.enabled);
		return enabled;
	}
}
