package net.datasa.web4_practice.service;

import lombok.RequiredArgsConstructor;
import net.datasa.web4_practice.domain.dto.ChickenDto;
import net.datasa.web4_practice.domain.entity.ChickenEntity;
import net.datasa.web4_practice.repository.ChickenRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChickenService {
	private final ChickenRepository chickenRepository;
	
	public ChickenDto saveOrder(ChickenDto dto) {
		ChickenEntity entity = ChickenEntity.builder()
				.chickenType(dto.getChickenType())
				.chickenPrice(dto.getChickenPrice())
				.quantity(dto.getQuantity())
				.extraOptions(dto.getExtraOptions())
				.extraTotalPrice(dto.getExtraTotalPrice())
				.deliveryType(dto.getDeliveryType())
				.deliveryPrice(dto.getDeliveryPrice())
				.totalPrice(dto.getTotalPrice())
				.build();
		
		ChickenEntity saved = chickenRepository.save(entity);
		
		return ChickenDto.builder()
				.id(saved.getId())
				.chickenType(saved.getChickenType())
				.chickenPrice(saved.getChickenPrice())
				.quantity(saved.getQuantity())
				.extraOptions(saved.getExtraOptions())
				.extraTotalPrice(saved.getExtraTotalPrice())
				.deliveryType(saved.getDeliveryType())
				.deliveryPrice(saved.getDeliveryPrice())
				.totalPrice(saved.getTotalPrice())
				.orderDate(saved.getOrderDate())
				.build();
	}
	
	public List<ChickenDto> getOrderList() {
		return chickenRepository.findAll().stream()
				.sorted(Comparator.comparing(ChickenEntity::getId).reversed())
				.map(entity -> ChickenDto.builder()
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
						.build())
				.toList();
	}
	
	public void deleteOrder(Integer id) {
		chickenRepository.deleteById(id);
	}
}
