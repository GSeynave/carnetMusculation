package com.muscu.carnetMusculation.services.impl;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.dto.SerieAPI;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.entities.Serie;
import com.muscu.carnetMusculation.repositories.SerieRepository;
import com.muscu.carnetMusculation.services.SerieService;


@Service
public class SerieServiceImpl implements SerieService {
	@Autowired
	private SerieRepository serieRepository;
	
	@Override
	@Transactional
	public List<Serie> findBySeanceIdAndNumeroSerie(Long seanceId, String numeroSerie) {
		return this.serieRepository.findBySeanceIdAndNumeroSerie(seanceId, numeroSerie);
	}

	@Override
	public Serie save(Serie serie) {
		return this.serieRepository.save(serie);
	}

	@Override
	public List<Serie> findBySeanceId(Long seanceId) {
		return this.serieRepository.findBySeanceId(seanceId);
	}

	@Override
	public void deleteByIds(List<Long> ids) {
		this.serieRepository.deleteByIdIn(ids);;
	}

	@Override
	public Serie findBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId(Long seanceId, String numeroSerie, Long entrainementId, Long exerciceId) {
		return this.serieRepository.findBySeanceIdAndNumeroSerieAndEntrainementIdAndExerciceId(seanceId, numeroSerie, entrainementId, exerciceId);
	}

	@Override
	public void deleteByEntrainementIdAndSeanceIdAndExerciceIdIn(long entrainementId, Long seanceId, List<Long> exerciceIdList) {
		this.serieRepository.deleteByEntrainementIdAndSeanceIdAndExerciceIdIn(entrainementId, seanceId, exerciceIdList);
		
	}

	@Override
	public boolean existsBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId(Long seanceId, String numeroSerie,
			long exerciceId, Long entrainementId) {
		return this.serieRepository.existsBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId(seanceId, numeroSerie, exerciceId, entrainementId);
	}
}
