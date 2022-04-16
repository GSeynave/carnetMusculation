package com.muscu.carnetMusculation.services.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.EntrainementExercice;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.repositories.IEntrainementExerciceRepository;
import com.muscu.carnetMusculation.repositories.IExerciceRepository;
import com.muscu.carnetMusculation.services.IExerciceService;

@Service
public class ExerciceServiceImpl implements IExerciceService {

	@Autowired
	private IEntrainementExerciceRepository detailsRepository;
	@Autowired
	private IExerciceRepository exerciceRepository;

	@Transactional
	@Override
	public List<EntrainementExercice> findByEntrainementId(Long entrainementId) {
		return this.detailsRepository.findAllByEntrainementId(entrainementId);

	}

	@Transactional
	@Override
	public List<Exercice> findAll() {
		return this.exerciceRepository.findAll();
	}

	@Override
	public Exercice findById(Long exerciceId) {
		return this.exerciceRepository.findById(exerciceId);
	}

	@Override
	public void deleteById(Long exerciceId) {
		this.exerciceRepository.deleteById(exerciceId);
	}

	@Override
	public EntrainementExercice findByEntrainementIdAndExerciceId(long id, Long exerciceId) {
		return this.detailsRepository.findByEntrainementIdAndExerciceId(id, exerciceId);
	}

	@Override
	public void deleteDetailsByIds(List<Long> detailsToDelete) {
		this.detailsRepository.deleteAllById(detailsToDelete);
		
	}

	@Override
	public void deleteByEntrainementIdAndExerciceIdIn(long id, List<Long> exerciceIdList) {
		this.detailsRepository.deleteByEntrainementIdAndExerciceIdIn(id, exerciceIdList);
		
	}
}
