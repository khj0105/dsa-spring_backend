package net.datasa.web5.domain.entity;

import jakarta.persistence.*;
import lombok.*;

/*
	회원 정보 Entity
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "member")
public class MemberEntity {
	
	@Id
	@Column(name = "member_id", length = 30)
	String memberId;
	
	@Column(name = "member_password", length = 100)
	String memberPassword;
	
	@Column(name = "member_name", nullable = false, length = 30)
	String memberName;
	
	@Column(name = "email", length = 50)
	String email;
	
	@Column(name = "phone", length = 30)
	String phone;
	
	@Column(name = "address", length = 200)
	String address;
	
	@Column(name = "enabled")
	Boolean enabled;
	
	@Column(name = "rolename")
	String rolename;
	
	// @PrePersist는 insert 시점에 작동
	// DB에 INSERT 되기 전에 실행되는 콜백 메서드를 지정하는 어노테이션
	@PrePersist
	public void perPersist() {
		if (enabled == null) this.enabled = true;
		if (rolename == null) this.rolename = "ROLE_USER";
	}
}
