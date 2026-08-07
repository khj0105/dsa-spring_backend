package net.datasa.survey.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.survey.domain.dto.PerfumeDTO;
import net.datasa.survey.domain.entity.PerfumeEntity;
import net.datasa.survey.repository.PerfumeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
   Service
 */
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PerfumeService {
	
	private final PerfumeRepository pr;
	
	public void save(PerfumeDTO dto) {
		
		PerfumeEntity entity = PerfumeEntity.builder()
				.name(dto.getName())
				.gender(dto.getGender())
				.age(dto.getAge())
				.favoriteScent(dto.getFavoriteScent())
				.usageFrequency(dto.getUsageFrequency())
				.favoriteBrand(dto.getFavoriteBrand())
				.purchaseBudget(dto.getPurchaseBudget())
				.comments(dto.getComments())
				.completionTime(dto.getCompletionTime())
				.build();
		
		pr.save(entity);
	}
	
	public List<PerfumeDTO> selectAll() {
		List<PerfumeEntity> entityList = pr.findAll();
		List<PerfumeDTO> dtoList = new ArrayList<>();
		
		for (PerfumeEntity entity : entityList) {
			PerfumeDTO dto = PerfumeDTO.builder()
					.no(entity.getNo())
					.name(entity.getName())
					.gender(entity.getGender())
					.age(entity.getAge())
					.favoriteScent(entity.getFavoriteScent())
					.usageFrequency(entity.getUsageFrequency())
					.favoriteBrand(entity.getFavoriteBrand())
					.purchaseBudget(entity.getPurchaseBudget())
					.comments(entity.getComments())
					.completionTime(entity.getCompletionTime())
					.build();
			
			dtoList.add(dto);
		}
		return dtoList;
	}
}
