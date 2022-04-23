package com.muscu.carnetMusculation.services.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.EntrainementExercice;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.repositories.EntrainementExerciceRepository;
import com.muscu.carnetMusculation.repositories.ExerciceRepository;

@Service
public class ExerciceService {
	private final EntrainementExerciceRepository detailsRepository;
	private final ExerciceRepository exerciceRepository;

	public ExerciceService(EntrainementExerciceRepository detailsRepository, ExerciceRepository exerciceRepository) {
		super();
		this.detailsRepository = detailsRepository;
		this.exerciceRepository = exerciceRepository;
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
}
