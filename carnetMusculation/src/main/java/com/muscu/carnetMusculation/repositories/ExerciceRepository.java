package com.muscu.carnetMusculation.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.entities.Seance;

public interface ExerciceRepository extends CrudRepository<Exercice, Long> {
	@Query(value = "SELECT * FROM exercice AS e "
			+ "INNER JOIN seance_exercices AS s ON e.id = s.exercices_id "
			+ "WHERE s.seance_id = ?1", nativeQuery=true)
	List<Exercice> findBySeancesId(Long id);

}
