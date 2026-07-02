package net.datasa.web3.service;

import net.datasa.web3.domain.dto.PersonDto;

import java.util.List;

public interface PersonService {
	
	void test();
	// void save(PersonDto dto);
	
	void insert(PersonDto dto);
	
	PersonDto select(String searchId);
	
	void delete(String deleteId);
	
	List<PersonDto> selectAll();
	
	void update(PersonDto dto);
}
