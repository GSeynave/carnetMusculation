package com.muscu.carnetMusculation.services;

import java.util.List;

import com.muscu.carnetMusculation.entities.Programme;

public interface IProgrammeService {
	
	Programme findById(Long id);
	
	Programme save(Programme programme);
	
	List<Programme> findAll();
	
	void deleteById(Long id);
	
	boolean existsById(Long id);
	
	List<Programme> findPaginated(int page, int size, String sort);
	
	int countAll();
	
}
