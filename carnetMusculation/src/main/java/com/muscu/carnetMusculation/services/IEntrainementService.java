package com.muscu.carnetMusculation.services;

import java.util.List;

import com.muscu.carnetMusculation.entities.Entrainement;

public interface IEntrainementService {

	List<Entrainement> findByProgrammeId(Long id);
}
