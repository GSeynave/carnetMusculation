package com.muscu.carnetMusculation.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.DetailsExercice;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.repositories.IDetailsExerciceRepository;
import com.muscu.carnetMusculation.repositories.IExerciceRepository;
import com.muscu.carnetMusculation.services.IExerciceService;

@Service
public class ExerciceServiceImpl implements IExerciceService {

	@Autowired
	private IDetailsExerciceRepository detailsRepository;
	@Autowired
	private IExerciceRepository exerciceRepository;

	@Transactional
	@Override
	public List<DetailsExercice> findByEntrainementId(Long entrainementId) {
		return this.detailsRepository.findAllByEntrainementId(entrainementId);

	}

	@Transactional
	@Override
	public List<Exercice> findAll() {
		return this.exerciceRepository.findAll();
	}
}
