package net.datasa.web4.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestbookDTO {
	private Integer num;				// 글번호, 기본키
	private String name;				// 작성자 이름
	private String password;			// 비밀번호
	private String message;				// 게시글 내용
	private LocalDateTime inputdate;	// 작성시간
	private Integer recommendCnt;		// 추천수
}
