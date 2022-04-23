package com.muscu.carnetMusculation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.muscu.carnetMusculation.entities.Serie;

public interface SerieRepository extends PagingAndSortingRepository<Serie, Long>{

	@Query("SELECT s"
		+ " FROM Serie s"
		+ " WHERE s.exercice = :exerciceId")
	List<Serie> findByExerciceId(@Param("exerciceId") Long id);
	
	@Query("SELECT s"
		+ " FROM Serie s"
		+ " WHERE s.seance = :seanceId"
		+ " AND s.numeroSerie = :numeroSerie")
	List<Serie> findBySeanceIdAndNumeroSerie(
			@Param("seanceId") Long seanceId,
			@Param("numeroSerie") String numeroSerie);
	
	@Query("SELECT s"
		+ " FROM Serie s"
		+ " WHERE s.seance = :seanceId")
	List<Serie> findBySeanceId(@Param("seanceId") Long seanceId);
	
	@Query("SELECT s"
		+ " FROM Serie s"
		+ " WHERE s.seance = :seanceId"
		+ " AND s.numeroSerie = :numeroSerie"
		+ " AND s.entrainement = :entrainementId"
		+ " AND s.exercice = :exerciceId")
	Serie findBySeanceIdAndNumeroSerieAndEntrainementIdAndExerciceId(
			@Param("seanceId") Long seanceId,
			@Param("numeroSerie") String numeroSerie,
			@Param("entrainementId") Long entrainementId,
			@Param("exerciceId") Long exerciceId);
	
	@Query("DELETE"
		+ " FROM Serie s"
		+ " WHERE s.entrainement = :entrainementId"
		+ " AND s.seance = :seanceId"
		+ " AND s.exercice in :exerciceIds")
	void deleteByEntrainementIdAndSeanceIdAndExerciceIdIn(
			@Param("entrainementId") long entrainementId,
			@Param("seanceId") Long seanceId,
			@Param("exerciceIds") List<Long> exerciceIds);
	
	Serie save(Serie serie);
	
	@Query("DELETE"
		+ " FROM Serie s"
		+ " WHERE s.id in :ids")
	void deleteByIdIn(@Param("ids") List<Long> ids);
	
	@Query("SELECT new java.lang.Boolean(count(s) > 0)"
			+ " FROM Serie s"
			+ " WHERE s.seance = :seanceId"
			+ " AND s.numeroSerie = :numeroSerie"
			+ " AND s.exercice = :exerciceId"
			+ " AND s.entrainement = :entrainementId")
	boolean existsBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId(
			@Param("seanceId") Long seanceId,
			@Param("numeroSerie") String numeroSerie,
			@Param("exerciceId") long exerciceId,
			@Param("entrainementId") long entrainementId);
}
