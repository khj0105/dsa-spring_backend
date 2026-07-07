package net.datasa.web4.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
	guestbook 테이블과 매핑되는 엔티티
	JPA Auditing(@CreatedDate)로 작성시간 자동 관리
	
	@EntityListeners(AuditingEntityListener.class)
	Spring Data JPA의 Auditing 기능을 사용하기 위해
	엔티티 클래스에 추가하는 Annotation.
		- Entity의 생성 및 수정 시점에 자동으로 특정 필드(생성일, 수정일...)을 업데이트
		- JPA의 Auditing 기능: Entity의 생성 및 수정 시점에 자동으로
			특정 필드를 기록할 수 있도록 도와주는 기능.
			
		EX. Entity 클래스의 멤버변수에 붙는 Annotation들..
			@CreateDate			최초 저장시 시간 자동 저장
			@LastModifiedDate	수정될 때마다 시간 자동 갱신
			@CreatedBy			최초 저장시 작성자 저장
			@LastModifiedBy		수정시 작성자 저장
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "guestbook")
public class GuestbookEntity {
	
	@Id
	/*
		GeneratedValue: Primary Key 의 키 생성전략을 설정
			- Generation.IDENTITY: MySQL의 AUTO_INCREMENT 방식을 이용
			- Generation.AUTO: JPA 구현체(Hibernate)가 생성 방식을 결정
			- Generation.SEQUENCE: DB의 SEQUENCE를 이용하여 키를 생성
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	
	@Column(name = "message", nullable = false, columnDefinition = "text")
	private String message;
	
	@CreatedDate
	@Column(name = "inputdate", updatable = false)
	private LocalDateTime inputdate;
	
	@Builder.Default
	@Column(name = "recommend_cnt", nullable = false)
	private Integer recommendCnt = 0;
	
	// 추천 증가 메서드
	public void increaseRecommend() {
		this.recommendCnt++;
	}
}
