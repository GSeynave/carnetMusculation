package com.muscu.carnetMusculation.services;

import java.util.List;

import com.muscu.carnetMusculation.dto.SerieAPI;
import com.muscu.carnetMusculation.entities.Serie;

public interface ISerieService {

	Serie saveBySerieAPI(SerieAPI serieApi);
	List<Serie> findBySeanceIdAndNumeroSerie(Long seanceId, String numeroSerie);
}
