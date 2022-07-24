package com.muscu.carnetMusculation.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.dto.ExercicePerformance;
import com.muscu.carnetMusculation.dto.Performance;
import com.muscu.carnetMusculation.entities.Entrainement;
import com.muscu.carnetMusculation.entities.EntrainementExercice;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.entities.Serie;
import com.muscu.carnetMusculation.repositories.EntrainementExerciceRepository;
import com.muscu.carnetMusculation.repositories.ExerciceRepository;

@Service
public class ExerciceService {
	private final EntrainementExerciceRepository detailsRepository;
	private final SeanceService seanceService;
	private final SerieService serieService;
	private final ExerciceRepository exerciceRepository;

	public ExerciceService(EntrainementExerciceRepository detailsRepository, ExerciceRepository exerciceRepository, SeanceService seanceService, SerieService serieService) {
		super();
		this.detailsRepository = detailsRepository;
		this.exerciceRepository = exerciceRepository;
		this.seanceService = seanceService;
		this.serieService = serieService;
	}

	@Transactional
	public List<EntrainementExercice> findByEntrainementId(Long entrainementId) {
		return this.detailsRepository.findAllByEntrainementId(entrainementId);
	}

	@Transactional
	public List<Exercice> findAll() {
		return this.exerciceRepository.findAll();
	}

	@Transactional
	public Exercice findById(Long exerciceId) {
		Optional<Exercice> exercice = this.exerciceRepository.findById(exerciceId);
		if (exercice.isPresent()) {
			return exercice.get();
		} else {
			throw new EntityNotFoundException("Exercice non trouvé pour l'id : " +exerciceId);
		}
	}

	@Transactional
	public void deleteById(Long exerciceId) {
		this.exerciceRepository.deleteById(exerciceId);
	}

	@Transactional
	public EntrainementExercice findByEntrainementIdAndExerciceId(long id, Long exerciceId) {
		return this.detailsRepository.findByEntrainementIdAndExerciceId(id, exerciceId);
	}

	@Transactional
	public void deleteDetailsByIds(List<Long> detailsToDelete) {
		this.detailsRepository.deleteAllById(detailsToDelete);
		
	}

	@Transactional
	public void deleteByEntrainementIdAndExerciceIdIn(long id, List<Long> exerciceIdList) {
		this.detailsRepository.deleteByEntrainementIdAndExerciceIdIn(id, exerciceIdList);
	}

	@Transactional
	public boolean existsById(Long exerciceId) {
		return this.exerciceRepository.existsById(exerciceId);
	}

	@Transactional
	public boolean existsByEntrainementId(long entrainementId) {
		return this.detailsRepository.existsByEntrainementId(entrainementId);
	}
	
	@Transactional
	public boolean existsByExerciceIdAndEntrainementId(long exerciceId, long entrainementId) {
		return this.detailsRepository.existsByExerciceIdAndEntrainementId(exerciceId, entrainementId);
	}

	@Transactional
	public List<ExercicePerformance> findBySeanceId(Long seanceId) {
		Seance seance = this.seanceService.findById(seanceId);
		Entrainement entrainement = seance.getEntrainement();
		
		List<EntrainementExercice> entrainementExercices = this.findByEntrainementId(entrainement.getId());
		List<Long> exerciceIds = new ArrayList<Long>();
		entrainementExercices.forEach(data -> {
			exerciceIds.add(data.getExercice().getId());	
		});
		
		List<Exercice> exercices = this.exerciceRepository.findAllByIds(exerciceIds);
		
		List<ExercicePerformance> exercicePerformances = new ArrayList<ExercicePerformance>();
		
		exercices.forEach(exercice -> {
			ExercicePerformance exercicePerformance = new ExercicePerformance();
			exercicePerformance.setExercice(exercice.getNom());
			List<Serie> series = this.serieService.findBySeanceIdAndExerciceId(seanceId, exercice.getId());
			List<Performance> performances = new ArrayList<Performance>();
			series.forEach(serie -> {
				Performance performance = new Performance();
				performance.setRep(serie.getRep());
				performance.setPoids(serie.getPoids());
				performances.add(performance);
			});
			exercicePerformance.setPerformance(performances);		
			exercicePerformances.add(exercicePerformance);
		});
		return exercicePerformances;
	}
}
