package com.muscu.carnetMusculation.services;

import java.util.List;

import com.muscu.carnetMusculation.dto.SerieAPI;
import com.muscu.carnetMusculation.entities.Serie;

public interface ISerieService {

	Serie save(Serie serie);
	Serie saveBySerieAPI(SerieAPI serieApi);
	List<Serie> findBySeanceIdAndNumeroSerie(Long seanceId, String numeroSerie);
	Serie findBySeanceIdAndNumeroSerieAndExerciceId(Long seanceId, String numeroSerie, Long exerciceId);
	List<Serie> findBySeanceId(Long seanceId);
	void deleteByIds(List<Long> ids);
}
