package net.datasa.ex3.repository;

import net.datasa.ex3.domain.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository
 * DB와 연결된 데이터 처리 담당 계층
 */
@Repository
public interface StudentRepository 
	// JPA 기능을 사용하기 위한 상속, 제네릭타입은 <Entity클래스, PK의 데이터타입>
	extends JpaRepository<StudentEntity, Integer> {

}
