package net.datasa.web5_practice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="restaurant")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int restaurantNum;
	
	private String name;
	private String category;
	private String address;
	private String detailAddress;
	private String phone;
	private int rating;
	private String description;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
}
