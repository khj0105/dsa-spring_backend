package net.datasa.survey.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
   Entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "perfume")
public class PerfumeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "no")
	private Integer no;                        // 설문 번호
	
	@Column(name = "name", length = 30, nullable = false)
	private String name;                    // 이름
	
	@Column(name = "gender", length = 10)
	private String gender;                    // 성별
	
	@Column(name = "age", nullable = false)
	private int age;                    // 나이
	
	@Column(name = "favorite_scent", length = 50)
	private String favoriteScent;            // 선호하는 향
	
	@Column(name = "usage_frequency", length = 50)
	private String usageFrequency;            // 사용 빈도
	
	@Column(name = "favorite_brand", length = 50)
	private String favoriteBrand;            // 선호 브랜드
	
	@Column(name = "purchase_budget", length = 50)
	private String purchaseBudget;            // 구매 예산
	
	@Column(name = "comments", length = 200)
	private String comments;                // 추가 의견
	
	@CreatedDate
	@Column(name = "completion_time")
	private LocalDateTime completionTime;    // 설문 시간
}