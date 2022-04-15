package com.muscu.carnetMusculation.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.muscu.carnetMusculation.dto.Details;
import com.muscu.carnetMusculation.dto.EntrainementAPI;
import com.muscu.carnetMusculation.dto.EntrainementCreerAPI;
import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.entities.DetailsExercice;
import com.muscu.carnetMusculation.entities.Entrainement;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.entities.Serie;
import com.muscu.carnetMusculation.repositories.IDetailsExerciceRepository;
import com.muscu.carnetMusculation.repositories.IEntrainementRepository;
import com.muscu.carnetMusculation.services.IEntrainementService;
import com.muscu.carnetMusculation.services.IExerciceService;
import com.muscu.carnetMusculation.services.IProgrammeService;
import com.muscu.carnetMusculation.services.ISeanceService;
import com.muscu.carnetMusculation.services.ISerieService;
import com.muscu.carnetMusculation.utils.EntrainementType;
import com.muscu.carnetMusculation.utils.SeanceState;

@Service
public class EntrainementServiceImpl implements IEntrainementService {

	@Autowired
	private IEntrainementRepository entrainementRepository;
	@Autowired
	private IDetailsExerciceRepository detailsRepository;
	@Autowired
	private ISeanceService seanceService;
	@Autowired
	private ISerieService serieService;
	@Autowired
	private IProgrammeService programmeService;
	@Autowired
	private IExerciceService exerciceService;
	@Autowired
	private MapperAPI mapperApi;

	private final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-mm-DD");

	@Transactional
	public Entrainement findById(Long id) {
		return this.entrainementRepository.findById(id).orElse(null);
	}

	@Transactional
	public List<Entrainement> findByProgrammeId(Long id) {
		return this.entrainementRepository.findByProgrammeId(id);
	}

	@Transactional
	public DetailsExercice findDetailsByEntrainementIdAndExerciceId(Long exerciceId, Long entrainementId) {
		return this.detailsRepository.findByEntrainementIdAndExerciceId(exerciceId, entrainementId);
	}

	@Transactional
	public EntrainementCreerAPI creationEntrainement(EntrainementCreerAPI entrainementCreerApi) throws ParseException {
		EntrainementCreerAPI entrainementCreer = new EntrainementCreerAPI();

		Programme programme = this.programmeService.findById(entrainementCreerApi.getProgrammeId());
		entrainementCreerApi
				.setProgrammeId(this.programmeService.findById(entrainementCreerApi.getProgrammeId()).getId());

		Entrainement entrainement = this.entrainementRepository.findByNom(entrainementCreerApi.getNom());
		if (ObjectUtils.isEmpty(entrainement)) {
			entrainement = new Entrainement();
		}
		entrainement.setDateCreation(DATE_FORMATTER.parse(entrainementCreerApi.getCreationDate()));
		entrainement.setDateModification(DATE_FORMATTER.parse(entrainementCreerApi.getModificationDate()));
		entrainement.setNom(entrainementCreerApi.getNom());
		entrainement.setType(entrainementCreerApi.getType().getValue());
		entrainement.setProgramme(programme);
		entrainement = this.save(entrainement);
		entrainementCreer = setEntrainementCreerApi(entrainement);
		entrainementCreer.setProgrammeId(programme.getId());

		Seance seance = this.seanceService.findByEntrainementIdAndState(entrainement.getId(), SeanceState.INIT);
		if (ObjectUtils.isEmpty(seance)) {
			seance = setSeance(entrainement);
			seance = this.seanceService.save(seance);
		}

		List<Details> details = new ArrayList<Details>();
		List<Serie> series = new ArrayList<Serie>();
		List<DetailsExercice> detailsExercices = new ArrayList<DetailsExercice>();

		for (Details detail : entrainementCreerApi.getDetails()) {
			Details detailEntrainement = new Details();
			DetailsExercice detailExercice = this.exerciceService
					.findByEntrainementIdAndExerciceId(entrainement.getId(), detail.getExerciceId());
			if (ObjectUtils.isEmpty(detailExercice)) {
				detailExercice = new DetailsExercice();
			}
			detailExercice.setEntrainement(entrainement);
			Exercice exercice = new Exercice();
			exercice = this.exerciceService.findById(detail.getExerciceId());
			detailExercice.setExercice(exercice);
			detailExercice.setNbSerie(detail.getNbSerie());
			detailsExercices.add(this.detailsRepository.save(detailExercice));

			detailEntrainement.setExerciceId(exercice.getId());
			detailEntrainement.setNbSerie(detail.getNbSerie());

			Serie serie = this.serieService.findBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId(seance.getId(),
					"0", entrainement.getId(), exercice.getId());
			if (ObjectUtils.isEmpty(serie)) {
				serie = new Serie();
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

		List<Long> exerciceIdList = this.exerciceService.findByEntrainementId(entrainement.getId()).stream()
				.map(DetailsExercice::getExercice).map(Exercice::getId).collect(Collectors.toList());

		exerciceIdList.removeAll(entrainementCreerApi.getDetails().stream().map(Details::getExerciceId).collect(Collectors.toList()));
		
		this.serieService.deleteByEntrainementIdAndSeanceIdAndExerciceIdIn(entrainement.getId(), seance.getId(), exerciceIdList);
		this.exerciceService.deleteByEntrainementIdAndExerciceIdIn(entrainement.getId(), exerciceIdList);
		return entrainementCreer;
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

	@Override
	public boolean existsById(Long id) {
		return this.entrainementRepository.existsById(id);
	}

	@Override
	public void deleteById(Long id) {
		this.entrainementRepository.deleteById(id);
	}

	@Override
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
		List<DetailsExercice> detailsExercices = this.findDetailsByEntrainementId(entrainementId);
		List<Details> details = new ArrayList<Details>();

		for (DetailsExercice detailExercice : detailsExercices) {

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

	private List<DetailsExercice> findDetailsByEntrainementId(Long entrainementId) {
		return this.detailsRepository.findAllByEntrainementId(entrainementId);
	}

}
