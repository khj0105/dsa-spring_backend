package net.datasa.web4_practice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChickenDto {
	private Integer id;
	private String chickenType;
	private int chickenPrice;
	private int quantity;
	private String extraOptions;
	private int extraTotalPrice;
	private String deliveryType;
	private int deliveryPrice;
	private int totalPrice;
	private LocalDateTime orderDate;
}
