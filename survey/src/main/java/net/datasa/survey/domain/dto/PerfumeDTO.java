package net.datasa.survey.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
	DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfumeDTO {
	private Integer no;						// 설문 번호
	private String 	name;					// 이름
	private String 	gender;					// 성별
	private int 	age;					// 나이
	private String 	favoriteScent;			// 선호하는 향
	private String 	usageFrequency;			// 사용 빈도
	private String 	favoriteBrand;			// 선호 브랜드
	private String 	purchaseBudget;			// 구매 예산
	private String 	comments;				// 추가 의견
    private LocalDateTime completionTime;	// 설문 시간
}
