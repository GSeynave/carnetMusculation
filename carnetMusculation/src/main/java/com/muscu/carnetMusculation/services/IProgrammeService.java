package com.muscu.carnetMusculation.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.muscu.carnetMusculation.entities.Programme;

public interface IProgrammeService {
	
	Programme findById(Long id);
	
	void save(Programme programme);
	
	List<Programme> findAll(Pageable pageable);
	
	void deleteById(Long id);
	
//	boolean existsById(Long id);
	
	List<Programme> findPaginated(int page, int size, String sort);
	
	long countAll();
	
}
