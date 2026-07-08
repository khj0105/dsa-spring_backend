package net.datasa.web4.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
/*
	여러 개의 필드를 하나의 객체로 묶어서 엔티티 안에서 필드처럼 사용하게 해주는 기능
	-Entity가 아님
	-데이터베이스의 "컬럼 그룹"을 객체로 묶은 것
	-연관관계나 별도 테이블은 없음
 */
@Embeddable
public class GuestbookRecommendKey implements Serializable {/*Serializable는 직렬화*/
	
	@Column(name = "guestbook_num")
	private Integer guestbookNum;
	
	@Column(name = "ip", length = 50)
	private String ip;
}
