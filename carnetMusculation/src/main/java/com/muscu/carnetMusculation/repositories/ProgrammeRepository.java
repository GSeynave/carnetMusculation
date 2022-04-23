package com.muscu.carnetMusculation.repositories;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.muscu.carnetMusculation.entities.Programme;

@Repository
public interface ProgrammeRepository extends PagingAndSortingRepository<Programme, Long>{

	@Query("SELECT p"
		+ " FROM Programme p")
	Page<Programme> findAll(Pageable pageable);
	
	@Query("SELECT p"
		+ " FROM Programme p")
	List<Programme> findAll();

	@Query("SELECT p"
		+ " FROM Programme p"
		+ " WHERE p.id = :id")
	Programme findById(@Param("id") long id);
	
	@Query("SELECT count(p)"
		+ " FROM Programme p")
	long count();
	
	Programme save(Programme programme);
	
	@Query("DELETE"
		+ " FROM Programme p"
		+ " WHERE p.id = :id")
	void deleteById(long id);
}
