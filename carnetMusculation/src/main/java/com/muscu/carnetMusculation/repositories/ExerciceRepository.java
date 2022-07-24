package com.muscu.carnetMusculation.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.muscu.carnetMusculation.entities.Exercice;

public interface ExerciceRepository extends CrudRepository<Exercice, Long>{
	
	@Query("SELECT e"
		+ " FROM Exercice e")
	List<Exercice> findAll();

	@Query("SELECT e"
		+ " FROM Exercice e"
		+ " where e.id in (:ids)")
	List<Exercice> findAllByIds(List<Long> ids);
	
	@Query("SELECT e"
		+ " FROM Exercice e"
		+ " WHERE e.id = :id")
	Optional<Exercice> findById(@Param("id") long id);
	
	@Modifying
	@Query("DELETE"
		+ " FROM Exercice e"
		+ " WHERE e.id = :id")
	int deleteById(@Param("id") long id);
}
