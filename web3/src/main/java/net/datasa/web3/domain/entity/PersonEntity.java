package net.datasa.web3.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*
	DB - table 과 매핑되는 클래스 (person)
 */
@Data
// JPA에서 DB 테이블과 매핑되는 자바 클래스를 정의하기 위해 사용하는 Annotation
@Entity
// 엔티티가 매핑될 테이블을 지정
@Table(name = "person")
public class PersonEntity {
	@Id
	@Column(name = "id", nullable = false, length = 30)
	private String id;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "age")
	private Integer age;
}
