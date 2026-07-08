package net.datasa.web4.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web4.domain.dto.GuestbookDTO;
import net.datasa.web4.domain.entity.GuestbookEntity;
import net.datasa.web4.domain.entity.GuestbookRecommendEntity;
import net.datasa.web4.domain.entity.GuestbookRecommendKey;
import net.datasa.web4.exception.PasswordException;
import net.datasa.web4.exception.RecommendException;
import net.datasa.web4.repository.GuestbookRecommendRepository;
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
	private final GuestbookRecommendRepository  rr;
	
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
	
	// -------------------------------------------------------------------------
	/*
		비밀번호가 일치할 경우, 글 번호에 일치하는 게시글 정보 조회
		@param num
		@param password
		@return dto 해당 번호의 게시글 정보
		@throws EntityNotFoundException 해당 번호의 글이 없을 때
		@throws PasswordException 비밀번호가 틀릴 때
	 */
	public GuestbookDTO selectGuestbook(Integer num, String password) {
		GuestbookEntity entity = gr.findById(num)
				.orElseThrow(() -> new EntityNotFoundException(num + "번 글이 없습니다."));
		
		if (!entity.getPassword().equals(password)) {
			throw new PasswordException("비밀번호가 틀립니다.");
		}
		
		GuestbookDTO dto = GuestbookDTO.builder()
				.num(entity.getNum())
				.name(entity.getName())
				.password(entity.getPassword())
				.message(entity.getMessage())
				.build();
		
		return dto;
		
	}
	
	// -------------------------------------------------------------------------
	/*
		게시글 수정
		@param dto
	 */
	public void update(GuestbookDTO dto) {
		GuestbookEntity entity = gr.findById(dto.getNum())
				.orElseThrow(() -> new EntityNotFoundException(dto.getNum() + "번 글이 없습니다."));
		
		entity.setPassword(dto.getPassword());
		entity.setMessage(dto.getMessage());
	}
	
	// -------------------------------------------------------------------------
	/*
		추천 처리
		@param num
		@param clientIp
	 */
	public void recommend(Integer num, String clientIp) {
		// 파라미터 객체화
		GuestbookRecommendKey key = new GuestbookRecommendKey(num, clientIp);
		
		// 1. 이미 이 IP로 글을 추천했는지 확인
		boolean exists = rr.existsById(key);
		if (exists) {
			throw new RecommendException("이미 추천한 글입니다.");
		}
		
		// 2. 추천 이력 저장
		GuestbookRecommendEntity recommend = GuestbookRecommendEntity
				.builder()
				.id(key)
				.build();
		rr.save(recommend);
		
		// 3. 원글의 추천 수 +1
		GuestbookEntity guest = gr.findById(num)
				.orElseThrow(() -> new EntityNotFoundException(
						"글을 찾을 수 없습니다."
				));
		guest.increaseRecommend();
	}
}

