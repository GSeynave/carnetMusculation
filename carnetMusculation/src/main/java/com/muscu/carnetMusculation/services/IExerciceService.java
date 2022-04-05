package com.muscu.carnetMusculation.services;

import java.util.List;

import com.muscu.carnetMusculation.entities.DetailsExercice;
import com.muscu.carnetMusculation.entities.Exercice;

public interface IExerciceService {
	List<DetailsExercice> findByEntrainementId(Long entrainementId);
	List<Exercice> findAll();
	Exercice findById(Long exerciceId);
}
