package net.datasa.web3.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web3.domain.dto.PersonDto;
import net.datasa.web3.domain.entity.PersonEntity;
import net.datasa.web3.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
	@Transactional - 트랜잭션 처리
	클래스나 메서드에 적용할 수 있으며, 해당 메서드가 호출될 때 트랜잭션이 시작
		정상적으로 완료되면 트랜잭션이 커밋(Commit),
		예외가 발생하면 트랜잭션이 롤백(Rollback)
	- Spring의 @Transactional은 예외가 "밖으로 던져졌을 때만" 트랜잭션을 롤백 처리
	- 기본적으로는 체크되지 않은 예외 (RuntimeException, Error)만 롤백 대상
 */
@Transactional
@Service
@Slf4j
@RequiredArgsConstructor // 생성자 주입 방식
public class PersonServiceImpl implements PersonService{
	
	private final PersonRepository pr;
	
	@Override
	public void test() {
		// 임의의 데이터 생성
		PersonDto person = new PersonDto();
		person.setId("ccc");
		person.setName("아무개");
		person.setAge(30);
		
		PersonEntity personEntity = new PersonEntity();
		personEntity.setId(person.getId());
		personEntity.setName(person.getName());
		personEntity.setAge(person.getAge());
		
		pr.save(personEntity); // 저장 or 수정
		
		log.debug("--- [저장 테스트] ---");
		log.debug("entity: {}", personEntity);
	}
	
	@Override
	public void insert(PersonDto dto) {
		
		// 중복 체크
		String targetId = dto.getId();
		if (pr.existsById(targetId)) {
			throw new IllegalStateException("이미 존재하는 ID입니다.");
		}
		
		PersonEntity entity = new PersonEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		
		pr.save(entity);
	}
	
	@Override
	public PersonDto select(String searchId) {
		
		PersonEntity entity = pr.findById(searchId)
				.orElseThrow(
						() -> new EntityNotFoundException("회원이 존재하지 않습니다.")
				);
		
		PersonDto dto = new PersonDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setAge(entity.getAge());
		
		
		return dto;
	}
	
	@Override
	public void delete(String deleteId) {
		// ID를 기준으로 삭제
//		pr.deleteById(deleteId);
		
		PersonEntity entity = pr.findById(deleteId)
				.orElseThrow( () ->
						new EntityNotFoundException("회원이 존재하지 않습니다.")
						);
		
		pr.delete(entity);
	}
	
	@Override
	public List<PersonDto> selectAll() {
		
		List<PersonEntity> entityList = pr.findAll();
		List<PersonDto> dtoList = new ArrayList<>();
		
		for (PersonEntity entity : entityList) {
			PersonDto dto = new PersonDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setAge(entity.getAge());
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}

//	@Override
//	public void save(PersonDto dto) {
//		PersonEntity personEntity = new PersonEntity();
//		personEntity.setId(dto.getId());
//		personEntity.setName(dto.getName());
//		personEntity.setAge(dto.getAge());
//
//		pr.save(personEntity);
//	}
	
}
