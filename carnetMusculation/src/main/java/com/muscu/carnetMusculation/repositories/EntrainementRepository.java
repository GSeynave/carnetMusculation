package com.muscu.carnetMusculation.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.muscu.carnetMusculation.entities.Entrainement;

public interface EntrainementRepository extends PagingAndSortingRepository<Entrainement, Long>{

	@Query("SELECT e"
		+ " FROM Entrainement e"
		+ " WHERE e.id = :id")
	Optional<Entrainement> findById(@Param("id") long id);
	
	@Query("SELECT e"
		+ " FROM Entrainement e"
		+ " WHERE e.programme = :programmeId")
	List<Entrainement> findByProgrammeId(@Param("programmeId") Long programmeId);

	@Query("SELECT e"
		+ " FROM Entrainement e"
		+ " WHERE e.nom = :nom")
	Entrainement findByNom(@Param("nom") String nom);
	
	@Query("SELECT new java.lang.Boolean(count(e) > 0)"
		+ " FROM Entrainement e"
		+ " WHERE e.nom = :nom")
	Boolean existsByNom(@Param("nom") String nom);
	
	Entrainement save(Entrainement entrainement);

	@Query("SELECT new java.lang.Boolean(count(e) > 0)"
		+ " FROM Entrainement e"
		+ " WHERE e.id = :id")
	Boolean existsById(@Param("id") long id);
	
	@Query("DELETE"
		+ " FROM Entrainement e"
		+ " WHERE e.id = :id")
	void deleteById(@Param("id") long id);
}
