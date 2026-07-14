package net.datasa.ex4.domain.entity;

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
@Table(name = "chicken_order")
@EntityListeners(AuditingEntityListener.class)
public class ChickenEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;					// PK

    private String chickenType;			// 치킨 종류
    private int chickenPrice;			// 치킨 가격
    private int quantity;				// 수량
    private String extraOptions;		// 추가 옵션
    private int extraTotalPrice;		// 추가 옵션 가격
    private String deliveryType;		// 배달 종류
    private int deliveryPrice;			// 배달 비용
    private int totalPrice;				// 총 결제 금액

    @CreatedDate
    private LocalDateTime orderDate;	// 주문 시간
}
