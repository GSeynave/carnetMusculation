package com.muscu.carnetMusculation.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.muscu.carnetMusculation.entities.Programme;

@Repository
public interface IProgrammeRepository extends JpaRepository<Programme, Long> {

	Page<Programme> findAll(Pageable pageable);
	
	@Query(value = "SELECT count(*) FROM PROGRAMME", nativeQuery = true)
	int countAll();
}
