package net.datasa.web5_practice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5_practice.domain.dto.RestaurantDTO;
import net.datasa.web5_practice.domain.entity.RestaurantEntity;
import net.datasa.web5_practice.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class RestaurantService {
	
	private final RestaurantRepository rr;
	
	public List<RestaurantDTO> getRestaurantList() {
		List<RestaurantEntity> entityList = rr.findAll();
		List<RestaurantDTO> dtoList = new ArrayList<>();
		
		for (RestaurantEntity entity : entityList) {
			dtoList.add(convertToDTO(entity));
		}
		
		return dtoList;
	}
	

	public void write(RestaurantDTO dto) {
		RestaurantEntity entity = RestaurantEntity.builder()
				.name(dto.getName())
				.category(dto.getCategory())
				.address(dto.getAddress())
				.detailAddress(dto.getDetailAddress())
				.phone(dto.getPhone())
				.rating(dto.getRating())
				.description(dto.getDescription())
				.createDate(LocalDateTime.now())
				.build();
		
		log.debug("저장할 맛집 정보: {}", entity);
		rr.save(entity);
	}
	
	private RestaurantDTO convertToDTO(RestaurantEntity entity) {
		String stars = "★".repeat(entity.getRating()) + "☆".repeat(5 - entity.getRating());
		
		return RestaurantDTO.builder()
				.restaurantNum(entity.getRestaurantNum())
				.name(entity.getName())
				.category(entity.getCategory())
				.address(entity.getAddress())
				.detailAddress(entity.getDetailAddress())
				.phone(entity.getPhone())
				.rating(entity.getRating())
				.description(entity.getDescription())
				.starDisplay(stars)
				.createDate(entity.getCreateDate())
				.updateDate(entity.getUpdateDate())
				.build();
	}
}