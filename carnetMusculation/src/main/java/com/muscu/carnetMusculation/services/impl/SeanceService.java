package com.muscu.carnetMusculation.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.dto.Details;
import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.dto.SeanceAPI;
import com.muscu.carnetMusculation.dto.SeanceInformationInit;
import com.muscu.carnetMusculation.entities.Entrainement;
import com.muscu.carnetMusculation.entities.EntrainementExercice;
import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.entities.Serie;
import com.muscu.carnetMusculation.repositories.EntrainementExerciceRepository;
import com.muscu.carnetMusculation.repositories.SeanceRepository;
import com.muscu.carnetMusculation.utils.SeanceState;


@Service
public class SeanceService {
	private final SeanceRepository seanceRepository;
	private final SerieService serieService;
	private final EntrainementExerciceRepository detailsExerciceRepository;
	private final EntrainementService entrainementService;
	private final MapperAPI mapperApi;
	
	public SeanceService(@Lazy EntrainementService entrainementService, SeanceRepository seanceRepository,
			SerieService serieService, EntrainementExerciceRepository detailsExerciceRepository,
			MapperAPI mapperApi) {
		super();
		this.seanceRepository = seanceRepository;
		this.entrainementService = entrainementService;
		this.serieService = serieService;
		this.detailsExerciceRepository = detailsExerciceRepository;
		this.mapperApi = mapperApi;
	}

	@Transactional
	public Seance save(Seance seance) {
		return this.seanceRepository.save(seance);
	}
	
	@Transactional
	public Seance findById(Long seanceId) {
		return this.seanceRepository.findById(seanceId).get();
	}
	
	@Transactional
	public Seance saveBySeanceAPI(SeanceAPI seanceApi) {
		return this.save(mapperApi.convertToEntity(seanceApi));
	}

	@Transactional
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
			details.setExerciceId(mapperApi.convertToDto(serie.getExercice()).getId());
			details.setNom(serie.getExercice().getNom());
			details.setNbRep(serie.getRep());
			EntrainementExercice detailsExercice =  this.detailsExerciceRepository.findByEntrainementIdAndExerciceId(serie.getEntrainement().getId(), serie.getExercice().getId());
			details.setNbSerie(detailsExercice.getNbSerie());
			details.setRecup(serie.getRep());
			detailsList.add(details);
		}
		sii.setDetailsExercice(detailsList);
		return sii;
	}

	@Transactional
	public Seance findByEntrainementIdAndState(Long entrainementId, SeanceState state) {
		return this.seanceRepository.findByEntrainementIdAndState(entrainementId, state);
	}

	@Transactional
	public boolean existsByEntrainementIdAndState(long entrainementId, SeanceState state) {
		return this.seanceRepository.existsByEntrainementIdAndState(entrainementId, state);
	}

	public Seance createSeance(long entrainementId, SeanceState state) {
		Seance seance = new Seance();
		seance.setDateEntrainement(LocalDate.now());
		seance.setState(state);
		
		Entrainement entrainement = entrainementService.findById(entrainementId);
		seance.setEntrainement(entrainement);
		return save(seance);
	}

	@Transactional
	public SeanceInformationInit findSIIByProgrammeIdAndEntrainementIdAndState(Long programmeId, Long entrainementId, SeanceState seanceStateByCode) {
		Seance seance = this.findByProgrammeIdAndEntrainementIdAndState(programmeId, entrainementId, seanceStateByCode);
		SeanceInformationInit sii = new SeanceInformationInit();
		
		sii.setEntrainementId(seance.getEntrainement().getId());
		sii.setEntrainementNom(seance.getEntrainement().getNom());
		sii.setProgrammeId(seance.getEntrainement().getProgramme().getId());
		List<Serie> series = this.serieService.findBySeanceIdAndNumeroSerie(seance.getId(), "0");
		
		List<Details> detailsList = new ArrayList<>();
		
		for (Serie serie : series) {
			Details details = new Details();
			details.setExerciceId(mapperApi.convertToDto(serie.getExercice()).getId());
			details.setNom(serie.getExercice().getNom());
			details.setNbRep(serie.getRep());
			EntrainementExercice detailsExercice =  this.detailsExerciceRepository.findByEntrainementIdAndExerciceId(serie.getEntrainement().getId(), serie.getExercice().getId());
			details.setNbSerie(detailsExercice.getNbSerie());
			details.setRecup(serie.getRep());
			detailsList.add(details);
		}
		sii.setDetailsExercice(detailsList);
		return sii;
	}
	
	@Transactional
	public Seance findByProgrammeIdAndEntrainementIdAndState(Long programmeId, Long entrainementId, SeanceState state) {
		return this.seanceRepository.findByProgrammeIdAndEntrainementIdAndState(programmeId, entrainementId, state);
	}
}
