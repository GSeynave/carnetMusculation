package com.muscu.carnetMusculation.services.impl;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.Serie;
import com.muscu.carnetMusculation.repositories.SerieRepository;


@Service
public class SerieService {
	private final SerieRepository serieRepository;
	
	public SerieService(SerieRepository serieRepository) {
		super();
		this.serieRepository = serieRepository;
	}

	@Transactional
	public List<Serie> findBySeanceIdAndNumeroSerie(Long seanceId, String numeroSerie) {
		return this.serieRepository.findBySeanceIdAndNumeroSerie(seanceId, numeroSerie);
	}

	@Transactional
	public Serie save(Serie serie) {
		return this.serieRepository.save(serie);
	}

	@Transactional
	public List<Serie> findBySeanceId(Long seanceId) {
		return this.serieRepository.findBySeanceId(seanceId);
	}

	@Transactional
	public void deleteByIds(List<Long> ids) {
		this.serieRepository.deleteByIdIn(ids);;
	}

	@Transactional
	public Serie findBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId(Long seanceId, String numeroSerie, Long entrainementId, Long exerciceId) {
		return this.serieRepository.findBySeanceIdAndNumeroSerieAndEntrainementIdAndExerciceId(seanceId, numeroSerie, entrainementId, exerciceId);
	}

	@Transactional
	public void deleteByEntrainementIdAndSeanceIdAndExerciceIdIn(long entrainementId, Long seanceId, List<Long> exerciceIdList) {
		this.serieRepository.deleteByEntrainementIdAndSeanceIdAndExerciceIdIn(entrainementId, seanceId, exerciceIdList);
		
	}

	@Transactional
	public boolean existsBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId(Long seanceId, String numeroSerie,
			long exerciceId, Long entrainementId) {
		return this.serieRepository.existsBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId(seanceId, numeroSerie, exerciceId, entrainementId);
	}

	public List<Serie> findBySeanceIdAndExerciceId(Long seanceId, Long exerciceId) {
		return this.serieRepository.findBySeanceIdAndExericeId(seanceId, exerciceId);
	}
}
