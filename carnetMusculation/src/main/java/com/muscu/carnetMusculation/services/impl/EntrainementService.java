package com.muscu.carnetMusculation.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.muscu.carnetMusculation.dto.Details;
import com.muscu.carnetMusculation.dto.EntrainementAPI;
import com.muscu.carnetMusculation.dto.EntrainementCreerAPI;
import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.entities.Entrainement;
import com.muscu.carnetMusculation.entities.EntrainementExercice;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.entities.Serie;
import com.muscu.carnetMusculation.repositories.EntrainementExerciceRepository;
import com.muscu.carnetMusculation.repositories.EntrainementRepository;
import com.muscu.carnetMusculation.utils.EntrainementType;
import com.muscu.carnetMusculation.utils.SeanceState;

@Service
public class EntrainementService {

	private final EntrainementRepository entrainementRepository;
	private final EntrainementExerciceRepository detailsRepository;
	private final SeanceService seanceService;
	private final SerieService serieService;
	private final ProgrammeService programmeService;
	private final ExerciceService exerciceService;
	private final MapperAPI mapperApi;
	
	public EntrainementService(EntrainementRepository entrainementRepository,
			EntrainementExerciceRepository detailsRepository, SeanceService seanceService, SerieService serieService,
			ProgrammeService programmeService, ExerciceService exerciceService, MapperAPI mapperApi) {
		super();
		this.entrainementRepository = entrainementRepository;
		this.detailsRepository = detailsRepository;
		this.seanceService = seanceService;
		this.serieService = serieService;
		this.programmeService = programmeService;
		this.exerciceService = exerciceService;
		this.mapperApi = mapperApi;
	}


	@Transactional
	public Entrainement findById(Long id) {
		Optional<Entrainement> entrainement = this.entrainementRepository.findById(id);
		if (entrainement.isPresent()) {
			return entrainement.get();
		} else {
			throw new EntityNotFoundException("Entrainement non trouvé pour l'id : " + id);
		}
	}

	@Transactional
	public List<Entrainement> findByProgrammeId(Long id) {
		return this.entrainementRepository.findByProgrammeId(id);
	}

	@Transactional
	public EntrainementExercice findDetailsByEntrainementIdAndExerciceId(Long entrainementId, Long exerciceId) {
		return this.detailsRepository.findByEntrainementIdAndExerciceId(entrainementId, exerciceId);
	}

	@Transactional
	public EntrainementCreerAPI creationEntrainement(EntrainementCreerAPI entrainementCreerApi) throws ParseException {
		EntrainementCreerAPI entrainementCreer = new EntrainementCreerAPI();

		// Programme
		Programme programme = this.programmeService.findById(entrainementCreerApi.getProgrammeId());
		entrainementCreerApi.setProgrammeId(programme.getId());
		Entrainement entrainement = new Entrainement();
		if (this.existsByNom(entrainementCreerApi.getNom())) {
			entrainement = getEntrainementByNom(entrainementCreerApi.getNom());
		}
		
		// Entrainement
		entrainement.setDateCreation(LocalDate.parse(entrainementCreerApi.getCreationDate()));
		entrainement.setDateModification(LocalDate.parse(entrainementCreerApi.getModificationDate()));
		entrainement.setNom(entrainementCreerApi.getNom());
		entrainement.setType(entrainementCreerApi.getType().getValue());
		entrainement.setProgramme(programme);
		entrainement = this.save(entrainement);

		entrainementCreer = setEntrainementCreerApi(entrainement);
		entrainementCreer.setProgrammeId(programme.getId());
		// Seance
		Seance seance;
		if (this.seanceService.existsByEntrainementIdAndState(entrainement.getId(), SeanceState.INIT)) {
			seance = this.seanceService.findByEntrainementIdAndState(entrainement.getId(), SeanceState.INIT);
		} else {
			seance = setSeance(entrainement);
			seance = this.seanceService.save(seance);
		}

		// FIXME Ne plus remonter d'erreurs si liste série non modifiée
		// Details / Serie
		List<Details> details = new ArrayList<Details>();
		List<Serie> series = new ArrayList<Serie>();
		List<EntrainementExercice> detailsExercices = new ArrayList<EntrainementExercice>();

		for (Details detail : entrainementCreerApi.getDetails()) {
			Details detailEntrainement = new Details();
			EntrainementExercice detailExercice = this.exerciceService
					.findByEntrainementIdAndExerciceId(entrainement.getId(), detail.getExerciceId());
			if (detailExercice == null) {
				detailExercice = new EntrainementExercice();
				detailExercice.setEntrainement(entrainement);
			}
			Exercice exercice = new Exercice();
			if (this.exerciceService.existsById(detail.getExerciceId())) {
				exercice = this.exerciceService.findById(detail.getExerciceId());
			}

			detailExercice.setExercice(exercice);
			detailExercice.setNbSerie(detail.getNbSerie());
			detailsExercices.add(this.detailsRepository.save(detailExercice));

			detailEntrainement.setExerciceId(exercice.getId());
			detailEntrainement.setNbSerie(detail.getNbSerie());

			Serie serie = new Serie();
			if(this.serieService.existsBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId(seance.getId(),
						"0", exercice.getId(), entrainement.getId() )) {
				
				serie = this.serieService.findBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId(seance.getId(),
						"0", exercice.getId(), entrainement.getId());
			} 
			
			serie.setNumeroSerie("0");
			serie.setSeance(seance);
			serie.setExercice(exercice);
			serie.setEntrainement(entrainement);
			serie.setRep(detail.getNbRep());
			serie.setRecup(detail.getRecup());
			serie.setPoids("0");
			series.add(this.serieService.save(serie));

			detailEntrainement.setNbRep(serie.getRep());
			detailEntrainement.setRecup(serie.getRecup());
			details.add(detailEntrainement);
		}

		entrainementCreer.setDetails(details);

		// FIXME Retirer l'exercice manquant !!
		// Remove exercice
		List<Long> exerciceIdList;
		if(this.exerciceService.existsByEntrainementId(entrainement.getId())) {
			exerciceIdList = this.exerciceService.findByEntrainementId(entrainement.getId()).stream()
					.map(EntrainementExercice::getExercice).map(Exercice::getId).collect(Collectors.toList());

			exerciceIdList.removeAll(entrainementCreerApi.getDetails().stream().map(Details::getExerciceId)
					.collect(Collectors.toList()));
			this.serieService.deleteByEntrainementIdAndSeanceIdAndExerciceIdIn(entrainement.getId(), seance.getId(),
					exerciceIdList);
			this.exerciceService.deleteByEntrainementIdAndExerciceIdIn(entrainement.getId(), exerciceIdList);
		}

		return entrainementCreer;
	}

	private Entrainement getEntrainementByNom(String nom) {
		Entrainement entrainement = this.entrainementRepository.findByNom(nom);
		return ObjectUtils.isEmpty(entrainement) ? null : entrainement;
	}


	private Seance setSeance(Entrainement entrainement) {
		Seance seance = new Seance();
		seance.setEntrainement(entrainement);
		seance.setDateEntrainement(entrainement.getDateCreation());
		seance.setState(SeanceState.INIT);
		return seance;
	}

	private EntrainementCreerAPI setEntrainementCreerApi(Entrainement entrainement) {
		EntrainementCreerAPI entrainementCreer = new EntrainementCreerAPI();
		entrainementCreer.setEntrainementId(entrainement.getId());
		entrainementCreer.setCreationDate(entrainement.getDateCreation().toString());
		entrainementCreer.setModificationDate(entrainement.getDateModification().toString());
		entrainementCreer.setNom(entrainement.getNom());
		entrainementCreer.setType(EntrainementType.valueOf(entrainement.getType()));
		return entrainementCreer;
	}

	@Transactional
	public Entrainement save(EntrainementAPI entrainementApi) {
		Entrainement entrainement = mapperApi.convertToEntity(entrainementApi);
		return this.entrainementRepository.save(entrainement);
	}

	@Transactional
	public Entrainement save(Entrainement entrainement) {
		return this.entrainementRepository.save(entrainement);
	}

	@Transactional
	public boolean existsById(Long id) {
		return this.entrainementRepository.existsById(id);
	}

	@Transactional
	public boolean existsByNom(String nom) {
		return this.entrainementRepository.existsByNom(nom);
	}

	@Transactional
	public void deleteById(Long id) {
		this.entrainementRepository.deleteById(id);
	}

	@Transactional
	public EntrainementCreerAPI findSeanceInformationInitByEntrainementId(Long entrainementId) {
		EntrainementCreerAPI entrainementCreerApi = new EntrainementCreerAPI();
		Entrainement entrainement = this.findById(entrainementId);
		entrainementCreerApi.setProgrammeId(entrainement.getProgramme().getId());
		entrainementCreerApi.setEntrainementId(entrainement.getId());
		entrainementCreerApi.setNom(entrainement.getNom());
		entrainementCreerApi.setType(EntrainementType.valueOf(entrainement.getType()));
		entrainementCreerApi.setCreationDate(entrainement.getDateCreation().toString());
		entrainementCreerApi.setModificationDate(entrainement.getDateModification().toString());

		Seance seance = this.seanceService.findByEntrainementIdAndState(entrainementId, SeanceState.INIT);
		List<EntrainementExercice> detailsExercices = this.findDetailsByEntrainementId(entrainementId);
		List<Details> details = new ArrayList<Details>();

		for (EntrainementExercice detailExercice : detailsExercices) {

			Details detail = new Details();
			detail.setExerciceId(detailExercice.getExercice().getId());
			detail.setNbSerie(detailExercice.getNbSerie());

			Serie serie = this.serieService.findBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId(seance.getId(),
					"0", entrainementId, detailExercice.getExercice().getId());
			detail.setNbRep(serie.getRep());
			detail.setRecup(serie.getRecup());
			details.add(detail);
		}
		entrainementCreerApi.setDetails(details);

		return entrainementCreerApi;
	}

	@Transactional
	private List<EntrainementExercice> findDetailsByEntrainementId(Long entrainementId) {
		return this.detailsRepository.findAllByEntrainementId(entrainementId);
	}

}
