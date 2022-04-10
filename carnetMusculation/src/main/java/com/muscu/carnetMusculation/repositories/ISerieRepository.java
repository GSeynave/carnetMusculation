package com.muscu.carnetMusculation.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.muscu.carnetMusculation.entities.Serie;

public interface ISerieRepository extends CrudRepository<Serie, Long> {

	List<Serie> findByExerciceId(Long id);
	List<Serie> findBySeanceIdAndNumeroSerie(Long seanceId, String numeroSerie);
	List<Serie> findBySeanceId(Long seanceId);
	Serie findBySeanceIdAndNumeroSerieAndEntrainementIdAndExerciceId(Long seanceId, String numeroSerie, Long entrainementId,  Long exerciceId);
	void deleteByEntrainementIdAndSeanceIdAndExerciceIdIn(long entrainementId, Long seanceId, List<Long> exerciceIdList);
}
