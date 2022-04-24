package com.muscu.carnetMusculation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.muscu.carnetMusculation.entities.Exercice;

public interface ExerciceRepository extends PagingAndSortingRepository<Exercice, Long>{
	
	@Query("SELECT e"
		+ " FROM Exercice e")
	List<Exercice> findAll();
	
	@Query("SELECT e"
		+ " FROM Exercice e"
		+ " WHERE e.id = :id")
	Exercice findById(@Param("id") long id);
	
	@Modifying
	@Query("DELETE"
		+ " FROM Exercice e"
		+ " WHERE e.id = :id")
	void deleteById(long id);
}
