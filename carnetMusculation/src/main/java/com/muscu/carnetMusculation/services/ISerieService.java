package com.muscu.carnetMusculation.services;

import java.util.List;

import com.muscu.carnetMusculation.dto.SerieAPI;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.entities.Serie;

public interface ISerieService {

	Serie save(Serie serie);
	List<Serie> findBySeanceIdAndNumeroSerie(Long seanceId, String numeroSerie);
	List<Serie> findBySeanceId(Long seanceId);
	void deleteByIds(List<Long> ids);
	Serie findBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId(Long seanceId, String numeroSerie, Long entrainementId, Long exerciceId);
	void deleteByEntrainementIdAndSeanceIdAndExerciceIdIn(long entrainementId, Long seanceId, List<Long> exerciceIdList);
}
