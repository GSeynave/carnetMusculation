package com.muscu.carnetMusculation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.muscu.carnetMusculation.entities.Serie;

public interface SerieRepository extends PagingAndSortingRepository<Serie, Long>{

	@Query("SELECT s"
		+ " FROM Serie s"
		+ " WHERE s.exercice.id = :exerciceId")
	List<Serie> findByExerciceId(@Param("exerciceId") Long id);
	
	@Query("SELECT s"
		+ " FROM Serie s"
		+ " WHERE s.seance.id = :seanceId"
		+ " AND s.numeroSerie = :numeroSerie")
	List<Serie> findBySeanceIdAndNumeroSerie(
			@Param("seanceId") Long seanceId,
			@Param("numeroSerie") String numeroSerie);
	
	@Query("SELECT s"
		+ " FROM Serie s"
		+ " WHERE s.seance.id = :seanceId")
	List<Serie> findBySeanceId(@Param("seanceId") Long seanceId);
	
	@Query("SELECT s"
		+ " FROM Serie s"
		+ " WHERE s.seance.id = :seanceId"
		+ " AND s.numeroSerie = :numeroSerie"
		+ " AND s.entrainement.id = :entrainementId"
		+ " AND s.exercice.id = :exerciceId")
	Serie findBySeanceIdAndNumeroSerieAndEntrainementIdAndExerciceId(
			@Param("seanceId") Long seanceId,
			@Param("numeroSerie") String numeroSerie,
			@Param("entrainementId") Long entrainementId,
			@Param("exerciceId") Long exerciceId);
	
	@Modifying
	@Query("DELETE"
		+ " FROM Serie s"
		+ " WHERE s.entrainement.id = :entrainementId"
		+ " AND s.seance.id = :seanceId"
		+ " AND s.exercice.id in :exerciceIds")
	int deleteByEntrainementIdAndSeanceIdAndExerciceIdIn(
			@Param("entrainementId") long entrainementId,
			@Param("seanceId") Long seanceId,
			@Param("exerciceIds") List<Long> exerciceIds);
	
	Serie save(Serie serie);
	
	@Modifying
	@Query("DELETE"
		+ " FROM Serie s"
		+ " WHERE s.id in :ids")
	int deleteByIdIn(@Param("ids") List<Long> ids);
	
	@Query("SELECT new java.lang.Boolean(count(s) > 0)"
			+ " FROM Serie s"
			+ " WHERE s.seance.id = :seanceId"
			+ " AND s.numeroSerie = :numeroSerie"
			+ " AND s.exercice.id = :exerciceId"
			+ " AND s.entrainement.id = :entrainementId")
	boolean existsBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId(
			@Param("seanceId") Long seanceId,
			@Param("numeroSerie") String numeroSerie,
			@Param("exerciceId") long exerciceId,
			@Param("entrainementId") long entrainementId);


	@Query("SELECT s"
		+ " FROM Serie s"
		+ " WHERE s.seance.id = :seanceId"
		+ " AND s.exercice.id = :exerciceId")
	List<Serie> findBySeanceIdAndExericeId(
			@Param("seanceId") Long seanceId,
			@Param("exerciceId") Long exerciceId);
}
