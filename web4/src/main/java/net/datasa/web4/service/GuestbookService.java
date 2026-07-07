package net.datasa.web4.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web4.domain.dto.GuestbookDTO;
import net.datasa.web4.domain.entity.GuestbookEntity;
import net.datasa.web4.exception.PasswordException;
import net.datasa.web4.repository.GuestbookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class GuestbookService {
	
	private final GuestbookRepository gr;
	
	/*
		글 저장
		@param dto 저장할 글 내용
	 */
	public void write(GuestbookDTO dto) {
		
//		GuestbookEntity entity = GuestbookEntity.builder().build();
//			entity.setName(dto.getName());
//			entity.setPassword(dto.getPassword());
//			entity.setMessage(dto.getMessage());
			
		GuestbookEntity entity = GuestbookEntity.builder()
						.name(dto.getName())
						.password(dto.getPassword())
						.message(dto.getMessage())
						.build();
		
		gr.save(entity);
	}
	
	// -------------------------------------------------------------------------
	/*
		방명록 글을 작성일의 내림차순으로 모두 조회
		@return 방명록 글 목록
	 */
	public List<GuestbookDTO> getList() {
		
		/*
			Sort - Spring Data JPA 정렬 객체
			데이터 조회 시 정렬(ORDER BY)을 손쉽게 적용할 수 있도록 해주는
			유틸리티 클래스
			Sort 클래스는 구조화된 객체의 방식으로 정렬 기준을 지정하고,
			이를 기반으로 쿼리를 수행할 때 결과를 정렬할 수 있음.
		 */
		// 정렬 기준
		Sort sort = Sort.by(Sort.Direction.DESC, "inputdate");
		
		// 정렬 조건이 여러개 일때
		Sort sort2 = Sort.by(
				Sort.Order.desc("name").ignoreCase(),
				Sort.Order.asc("num"),
				Sort.Order.desc("inputdate")
		);
		Sort sort3 = Sort.by("inputdate").descending()
				.and(Sort.by("name").ascending());
		
		List<GuestbookEntity> entityList = gr.findAll(sort);
		List<GuestbookDTO> dtoList = new ArrayList<>();
		
		for (GuestbookEntity entity : entityList) {
			GuestbookDTO dto = GuestbookDTO.builder()
					.num(entity.getNum())
					.name(entity.getName())
					.message(entity.getMessage())
					.inputdate(entity.getInputdate())
					.recommendCnt(entity.getRecommendCnt())
					.build();
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	// -------------------------------------------------------------------------
	
	public void delete(Integer num, String password) {
		
		GuestbookEntity entity = gr.findById(num)
				.orElseThrow(() -> new EntityNotFoundException(num + "번 글이 없습니다."));
		
		if (!entity.getPassword().equals(password)) {
			throw new PasswordException("비밀번호가 틀립니다.");
		}
		
		gr.delete(entity);
		
	}
}
