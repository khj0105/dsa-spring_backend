package net.datasa.web3.repository;

import net.datasa.web3.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
	DB와 가장 가까이 연결이 되는 계층
	JpaRepository 라는 interface를 extends 하는 것 만으로,
	JPA에서 정의한 CRUD 메서드를 사용할 수 있다.
	
	JpaRepository 기본 제공 메서드
	메서드			설명
	--------------------------------------------
	save(entity)	INSERT or UPDATE
	findById(id)	ID 기준 조회
	findAll()		전체 조회
	delete(entity)	엔티티 삭제
	deleteById(id)	ID 기준 삭제
	count()			전체 개수 조회
 */
@Repository
public interface PersonRepository
		// JPA 기능을 사용하기 위한 상속, 제네릭타입은 <Entity 클래스, PK의 데이터 타입>
		extends JpaRepository<PersonEntity, String> { // 사용하려는 entity와 그 키 값

}
