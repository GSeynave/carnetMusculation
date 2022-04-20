package com.muscu.carnetMusculation.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.muscu.carnetMusculation.entities.Serie;

public interface SerieRepository extends PagingAndSortingRepository<Serie, Long>{

	List<Serie> findByExerciceId(Long id);
	List<Serie> findBySeanceIdAndNumeroSerie(Long seanceId, String numeroSerie);
	List<Serie> findBySeanceId(Long seanceId);
	Serie findBySeanceIdAndNumeroSerieAndEntrainementIdAndExerciceId(Long seanceId, String numeroSerie, Long entrainementId,  Long exerciceId);
	void deleteByEntrainementIdAndSeanceIdAndExerciceIdIn(long entrainementId, Long seanceId, List<Long> exerciceIdList);
	Serie save(Serie serie);
	void deleteByIdIn(List<Long> ids);
	boolean existsBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId(Long seanceId, String numeroSerie,
			long exerciceId, Long entrainementId);
}
