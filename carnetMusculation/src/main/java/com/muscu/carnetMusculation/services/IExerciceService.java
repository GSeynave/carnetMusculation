package com.muscu.carnetMusculation.services;

import java.util.List;

import com.muscu.carnetMusculation.entities.EntrainementExercice;
import com.muscu.carnetMusculation.entities.Exercice;

public interface IExerciceService {
	List<EntrainementExercice> findByEntrainementId(Long entrainementId);
	List<Exercice> findAll();
	Exercice findById(Long exerciceId);
	void deleteById(Long exerciceId);
	EntrainementExercice findByEntrainementIdAndExerciceId(long id, Long exerciceId);
	void deleteDetailsByIds(List<Long> detailsToDelete);
	void deleteByEntrainementIdAndExerciceIdIn(long id, List<Long> exerciceIdList);
}
