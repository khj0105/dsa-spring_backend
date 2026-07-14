package net.datasa.ex4.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.ex4.domain.dto.ChickenDTO;
import net.datasa.ex4.domain.entity.ChickenEntity;
import net.datasa.ex4.repository.ChickenRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ChickenService {

	private final ChickenRepository cr;

	// 주문 등록
	public void order(ChickenDTO dto) {
		
		ChickenEntity entity = ChickenEntity.builder().build();
		
		entity.setChickenType(dto.getChickenType());
		entity.setChickenPrice(dto.getChickenPrice());
		entity.setQuantity(dto.getQuantity());
		String extraOptions = dto.getExtraOptions();
		if (dto.getExtraOptions() == null || dto.getExtraOptions() == "") {
			extraOptions = "없음";
		}
		entity.setExtraOptions(extraOptions);
		entity.setExtraTotalPrice(dto.getExtraTotalPrice());
		entity.setDeliveryType(dto.getDeliveryType());
		entity.setDeliveryPrice(dto.getDeliveryPrice());
		entity.setTotalPrice(dto.getTotalPrice());
		
		cr.save(entity);
	}
	
	// 주문 전체 조회
	public List<ChickenDTO> list() {
		
		Sort sort = Sort.by(Sort.Direction.DESC, "orderDate");
		
		List<ChickenEntity> entityList = cr.findAll(sort);
		List<ChickenDTO> 	dtoList    = new ArrayList<>();
		
		for (ChickenEntity entity : entityList) {
			ChickenDTO dto = ChickenDTO.builder()
					.id(entity.getId())
					.chickenType(entity.getChickenType())
					.chickenPrice(entity.getChickenPrice())
					.quantity(entity.getQuantity())
					.extraOptions(entity.getExtraOptions())
					.extraTotalPrice(entity.getExtraTotalPrice())
					.deliveryType(entity.getDeliveryType())
					.deliveryPrice(entity.getDeliveryPrice())
					.totalPrice(entity.getTotalPrice())
					.orderDate(entity.getOrderDate())
					.build();
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	// 주문 삭제
	public void deleteOrder(Integer id) {
	
		ChickenEntity entity = cr.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("주문이 존재하지 않습니다."));
		
		cr.delete(entity);
	}
}
