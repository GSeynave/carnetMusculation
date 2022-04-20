package com.muscu.carnetMusculation.repositories;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.muscu.carnetMusculation.entities.Programme;

@Repository
public interface ProgrammeRepository extends PagingAndSortingRepository<Programme, Long>{

	Page<Programme> findAll(Pageable pageable);
	List<Programme> findAll();
	Programme findById(long id);
	long count();
	Programme save(Programme programme);
	void deleteById(long id);
}
