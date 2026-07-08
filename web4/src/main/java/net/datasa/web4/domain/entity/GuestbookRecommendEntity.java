package net.datasa.web4.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "guestbook_recommend")
@EntityListeners(AuditingEntityListener.class)
public class GuestbookRecommendEntity {
	
	// 복합키
	@EmbeddedId
	private GuestbookRecommendKey id;
	
	@CreatedDate
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
}
