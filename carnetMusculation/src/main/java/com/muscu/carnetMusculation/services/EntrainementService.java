package com.muscu.carnetMusculation.services;

import java.text.ParseException;
import java.util.List;

import com.muscu.carnetMusculation.dto.EntrainementAPI;
import com.muscu.carnetMusculation.dto.EntrainementCreerAPI;
import com.muscu.carnetMusculation.entities.EntrainementExercice;
import com.muscu.carnetMusculation.entities.Entrainement;

public interface EntrainementService {

	Entrainement findById(Long id);

	List<Entrainement> findByProgrammeId(Long id);
	
	EntrainementExercice findDetailsByEntrainementIdAndExerciceId(Long exerciceId, Long entrainementId);
	
	Entrainement save(EntrainementAPI entrainementApi);
	
	EntrainementCreerAPI creationEntrainement(EntrainementCreerAPI entrainementCreerApi) throws ParseException;

	boolean existsById(Long id);

	boolean existsByNom(String nom);

	void deleteById(Long id);

	EntrainementCreerAPI findSeanceInformationInitByEntrainementId(Long entrainementId);

}
