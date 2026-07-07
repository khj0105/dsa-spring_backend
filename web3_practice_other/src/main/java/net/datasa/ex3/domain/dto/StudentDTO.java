package net.datasa.ex3.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.datasa.ex3.domain.entity.StudentEntity;

/**
 * Student의 정보를 표현하기 위한 Object 구조.
 * Controller 계층과 Service 계층 간의 데이터 이동시에도 사용
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private int studentId;			// 학번
    private String name;			// 이름
    private String major;			// 전공
    private String java;			// JAVA
    private String db;				// DB
    private String web;				// WEB
    
    // DTO 에서 Entity 로 데이터 옮겨담기 메서드
    public static void Student_DTOtoEntity(StudentDTO dto, StudentEntity entity) {
    	entity.setStudentId(dto.getStudentId());
    	entity.setName(dto.getName());
    	entity.setMajor(dto.getMajor());
    	entity.setJava(dto.getJava());
    	entity.setDb(dto.getDb());
    	entity.setWeb(dto.getWeb());
    }
    
    // Entity 에서 DTO 로 데이터 옮겨담기 메서드
    public static void Student_EntityToDTO(StudentEntity entity, StudentDTO dto) {
    	dto.setStudentId(entity.getStudentId());
    	dto.setName(entity.getName());
    	dto.setMajor(entity.getMajor());
    	dto.setJava(entity.getJava());
    	dto.setDb(entity.getDb());
    	dto.setWeb(entity.getWeb());
    }
}
