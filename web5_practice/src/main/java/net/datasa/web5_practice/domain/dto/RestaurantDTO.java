package net.datasa.web5_practice.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDTO {
	private int restaurantNum;
	private String name;
	private String category;
	private String address;
	private String detailAddress;
	private String phone;
	private int rating;
	private String description;
	private String starDisplay;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
}
