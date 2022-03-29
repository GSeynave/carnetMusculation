package com.muscu.carnetMusculation.services.impl;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.dto.SerieAPI;
import com.muscu.carnetMusculation.entities.Serie;
import com.muscu.carnetMusculation.repositories.ISerieRepository;
import com.muscu.carnetMusculation.services.ISerieService;


@Service
public class SerieServiceImpl implements ISerieService {
	@Autowired
	private ISerieRepository serieRepository;
	@Autowired
	private MapperAPI mapperApi;

	@Override
	@Transactional
	public Serie saveBySerieAPI(SerieAPI serieApi) {
		Serie serie = mapperApi.convertToEntity(serieApi);
		return this.serieRepository.save(serie);
	}
	
	@Override
	@Transactional
	public List<Serie> findBySeanceIdAndNumeroSerie(Long seanceId, String numeroSerie) {
		return this.serieRepository.findBySeanceIdAndNumeroSerie(seanceId, numeroSerie);
	}
}
