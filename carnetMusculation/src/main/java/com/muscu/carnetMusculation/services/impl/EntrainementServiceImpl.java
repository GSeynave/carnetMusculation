package com.muscu.carnetMusculation.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.Entrainement;
import com.muscu.carnetMusculation.repositories.IEntrainementRepository;
import com.muscu.carnetMusculation.services.IEntrainementService;

@Service
public class EntrainementServiceImpl implements IEntrainementService {

	@Autowired
	private IEntrainementRepository entrainementRepository;
	
	public List<Entrainement> findByProgrammeId(Long id) {
		return this.entrainementRepository.findByProgrammeId(id);
	}
}
