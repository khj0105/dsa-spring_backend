package net.datasa.web3_practice.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.datasa.web3_practice.domain.dto.StudentDTO;
import net.datasa.web3_practice.domain.entity.StudentEntity;
import net.datasa.web3_practice.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {
	private final StudentRepository studentRepository;
	
	// 1. 전체 목록 조회
	public List<StudentDTO> selectAll() {
		List<StudentEntity> entities = studentRepository.findAll();
		List<StudentDTO> dtos = new ArrayList<>();
		for (StudentEntity entity : entities) {
			StudentDTO dto = new StudentDTO();
			StudentDTO.Student_EntityToDTO(entity, dto);
			dtos.add(dto);
		}
		return dtos;
	}
	
	// 2. 학생 등록
	public void insert(StudentDTO dto) {
		StudentEntity entity = new StudentEntity();
		StudentDTO.Student_DTOtoEntity(dto, entity);
		studentRepository.save(entity);
	}
	
	// 3. 한 명 조회
	public StudentDTO selectOne(int studentId) {
		StudentEntity entity = studentRepository.findById(studentId)
				.orElseThrow(() -> new EntityNotFoundException("해당 학생이 존재하지 않습니다. ID: " + studentId));
		StudentDTO dto = new StudentDTO();
		StudentDTO.Student_EntityToDTO(entity, dto);
		return dto;
	}
	
	// 4. 학생 정보 수정
	public void update(StudentDTO dto) {
		StudentEntity entity = studentRepository.findById(dto.getStudentId())
				.orElseThrow(() -> new EntityNotFoundException("수정할 학생이 없습니다. ID: " + dto.getStudentId()));
		
		// 기존 엔티티의 값을 변경 (Dirty Checking 활용)
		entity.setName(dto.getName());
		entity.setMajor(dto.getMajor());
		entity.setJava(dto.getJava());
		entity.setDb(dto.getDb());
		entity.setWeb(dto.getWeb());
	}
	
	// 5. 학생 삭제
	public void delete(int studentId) {
		StudentEntity entity = studentRepository.findById(studentId)
				.orElseThrow(() -> new EntityNotFoundException("삭제할 학생이 없습니다. ID: " + studentId));
		studentRepository.delete(entity);
	}
}
