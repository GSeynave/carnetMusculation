package com.muscu.carnetMusculation.services;

import java.util.List;

import com.muscu.carnetMusculation.dto.EntrainementAPI;
import com.muscu.carnetMusculation.dto.EntrainementCreerAPI;
import com.muscu.carnetMusculation.entities.DetailsExercice;
import com.muscu.carnetMusculation.entities.Entrainement;

public interface IEntrainementService {

	Entrainement findById(Long id);

	List<Entrainement> findByProgrammeId(Long id);
	
	DetailsExercice findDetailsByEntrainementIdAndExerciceId(Long exerciceId, Long entrainementId);
	
	Entrainement save(EntrainementAPI entrainementApi);
	
	Entrainement update(EntrainementCreerAPI entrainementApi);
	
	EntrainementAPI creationEntrainement(EntrainementCreerAPI entrainementCreerApi);

	boolean existsById(Long id);

	void deleteById(Long id);

}
