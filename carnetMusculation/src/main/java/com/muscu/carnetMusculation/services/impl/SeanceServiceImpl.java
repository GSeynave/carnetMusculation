package com.muscu.carnetMusculation.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.dto.Details;
import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.dto.SeanceAPI;
import com.muscu.carnetMusculation.dto.SeanceInformationInit;
import com.muscu.carnetMusculation.entities.DetailsExercice;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.entities.Serie;
import com.muscu.carnetMusculation.repositories.IDetailsExerciceRepository;
import com.muscu.carnetMusculation.repositories.ISeanceRepository;
import com.muscu.carnetMusculation.services.ISeanceService;
import com.muscu.carnetMusculation.services.ISerieService;
import com.muscu.carnetMusculation.utils.SeanceState;


@Service
public class SeanceServiceImpl implements ISeanceService {
	@Autowired
	private ISeanceRepository seanceRepository;
	@Autowired
	private ISerieService serieService;
	@Autowired
	private IDetailsExerciceRepository detailsExerciceRepository;
	@Autowired
	private MapperAPI mapperApi;
	
	@Override
	@Transactional
	public Seance save(Seance seance) {
		return this.seanceRepository.save(seance);
	}

	@Override
	@Transactional
	public Seance saveBySeanceAPI(SeanceAPI seanceApi) {
		return this.save(mapperApi.convertToEntity(seanceApi));
	}

	@Override
	public SeanceInformationInit findSIIByEntrainementIdAndState(Long entrainementId, SeanceState state) {
		Seance seance = this.findByEntrainementIdAndState(entrainementId, state);
		SeanceInformationInit sii = new SeanceInformationInit();
		
		sii.setEntrainementId(seance.getEntrainement().getId());
		sii.setEntrainementNom(seance.getEntrainement().getNom());
		sii.setProgrammeId(seance.getEntrainement().getProgramme().getId());
		List<Serie> series = this.serieService.findBySeanceIdAndNumeroSerie(seance.getId(), "0");
		
		List<Details> detailsList = new ArrayList<>();
		
		for (Serie serie : series) {
			Details details = new Details();
			details.setExercice(mapperApi.convertToDto(serie.getExercice()));
			details.setNbRep(serie.getRep());
			DetailsExercice detailsExercice =  this.detailsExerciceRepository.findByEntrainementIdAndExerciceId(serie.getEntrainement().getId(), serie.getExercice().getId());
			details.setNbSerie(detailsExercice.getNbSerie());
			details.setRecup(serie.getRep());
			detailsList.add(details);
		}
		sii.setDetailsExercice(detailsList);
		return sii;
	}

	@Override
	public Seance findByEntrainementIdAndState(Long entrainementId, SeanceState state) {
		return this.seanceRepository.findByEntrainementIdAndState(entrainementId, state);
	}
}
