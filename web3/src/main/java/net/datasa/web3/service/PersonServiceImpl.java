package net.datasa.web3.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web3.domain.dto.PersonDto;
import net.datasa.web3.domain.entity.PersonEntity;
import net.datasa.web3.repository.PersonRepository;
import org.springframework.stereotype.Service;

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
	public void save(PersonDto dto) {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setId(dto.getId());
		personEntity.setName(dto.getName());
		personEntity.setAge(dto.getAge());
		
		pr.save(personEntity);
	}
	
}
