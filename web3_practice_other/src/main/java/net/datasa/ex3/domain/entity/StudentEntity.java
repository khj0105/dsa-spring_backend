package net.datasa.ex3.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA는 Java의 클래스와 DB의 테이블을 매핑함.
 * StudentEntity(클래스) ↔ student(테이블)
 * Service 계층과 Repository 계층 간의 데이터 이동시 사용
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class StudentEntity {

    @Id							// 해당 멤버 변수를 Primary Key로 사용
    @Column(name = "sid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// MySQL의 AUTO_INCREMENT 와 매핑
    private int studentId;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "major")
    private String major;
    @Column(name = "java")
    private String java;
    @Column(name = "db")
    private String db;
    @Column(name = "web")
    private String web;
}
