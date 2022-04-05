package com.muscu.carnetMusculation.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.muscu.carnetMusculation.dto.Details;
import com.muscu.carnetMusculation.dto.EntrainementAPI;
import com.muscu.carnetMusculation.dto.EntrainementCreerAPI;
import com.muscu.carnetMusculation.dto.ExerciceAPI;
import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.dto.SeanceAPI;
import com.muscu.carnetMusculation.dto.SerieAPI;
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
	public EntrainementAPI creationEntrainement(EntrainementCreerAPI entrainementCreerApi) {
		EntrainementAPI entrainementCreer = new EntrainementAPI();
		Programme programme = this.programmeService.findById(entrainementCreerApi.getProgrammeId());
		entrainementCreerApi.setProgrammeId(this.programmeService.findById(entrainementCreerApi.getProgrammeId()).getId());
		
		// Save l'entrainement.
		Entrainement entrainement = new Entrainement();
		entrainement.setDateCreation(entrainementCreerApi.getCreationDate());
		entrainement.setNom(entrainementCreerApi.getNom());
		entrainement.setType(entrainementCreerApi.getType());
		entrainement.setProgramme(programme);
		entrainement = this.save(entrainement);
		entrainementCreer = mapperApi.convertToDto(entrainement);
		
		// Save séance
		SeanceAPI seanceApi = new SeanceAPI();
		seanceApi.setEntrainementId(entrainement.getId());
		seanceApi.setDateEntrainement(entrainement.getDateCreation());
		seanceApi.setSeanceState(SeanceState.INIT);
		Seance seance = this.seanceService.saveBySeanceAPI(seanceApi);
		entrainementCreer.setSeanceIds(List.of(seance.getId()));

		// Save details
		List<Long> detailsIds = new ArrayList<Long>();
		List<Long> serieIds = new ArrayList<Long>();
		
		for (Details detail : entrainementCreerApi.getDetails()) {
			DetailsExercice details = new DetailsExercice();
			details.setEntrainement(entrainement);
			Exercice exercice = new Exercice();
			exercice.setId(detail.getExercice().getId());
			details.setExercice(exercice);
			details.setNbSerie(detail.getNbSerie());
			detailsIds.add(this.detailsRepository.save(details).getId());
			
			// Save série "init" lié à la séance
			SerieAPI serieApi = new SerieAPI();
			// Set à zéro pour définir qu'il s'agit d'une séance 'init' permettant de
			// retrouver les données lors des prochaines séances.
			serieApi.setNumeroSerie(0);
			serieApi.setSeanceId(seance.getId());
			serieApi.setExerciceId(exercice.getId());
			serieApi.setEntrainementId(entrainement.getId());
			serieApi.setRep(detail.getNbRep());
			serieApi.setRecup(detail.getRecup());
			serieApi.setPoids(0);
			serieIds.add(this.serieService.saveBySerieAPI(serieApi).getId());
		}

		entrainementCreer.setDetails(detailsIds);
		entrainementCreer.setSerieIds(serieIds); 
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
	public Entrainement update(EntrainementCreerAPI entrainementApi) {
		
		Entrainement entrainement = this.findById(entrainementApi.getEntrainementId());
		Programme programme = this.programmeService.findById(entrainementApi.getProgrammeId());
		if(!ObjectUtils.isEmpty(entrainement)) {
			entrainement.setId(entrainementApi.getEntrainementId());
			entrainement.setProgramme(programme);
			entrainement.setDateModification(entrainementApi.getModificationDate());
			entrainement.setNom(entrainementApi.getNom());
			entrainement.setType(entrainementApi.getType());
			entrainement = this.save(entrainement);
		
			Seance seance = this.seanceService.findByEntrainementIdAndState(entrainement.getId(), SeanceState.INIT);
			List<Serie> series = this.serieService.findBySeanceId(seance.getId());
			List<DetailsExercice> detailsExercices = this.detailsRepository.findAllByEntrainementId(entrainement.getId());
			
			if(CollectionUtils.isEmpty(entrainementApi.getDetails())) {

				// Suppression des series / exercices qui ne sont plus dans la séance d'entrainement
				this.serieService.deleteByIds(series.stream().map(Serie::getId).collect(Collectors.toList()));
				this.detailsRepository.deleteAllById(detailsExercices.stream().map(DetailsExercice::getId).collect(Collectors.toList()));
			}
			else {
				for (Details detail : entrainementApi.getDetails()) {
					
					ExerciceAPI exerciceApi = detail.getExercice();
					Serie serie = this.serieService.findBySeanceIdAndNumeroSerieAndExerciceId(seance.getId(), "0", exerciceApi.getId());
					Exercice exercice = this.exerciceService.findById(exerciceApi.getId());
					DetailsExercice detailExercice = this.detailsRepository.findByEntrainementIdAndExerciceId(entrainement.getId(), exerciceApi.getId());
					
					if(ObjectUtils.isEmpty(serie)) {
						serie = new Serie();
						serie.setNumeroSerie("0");
					}
					serie.setRecup(detail.getRecup());
					serie.setRep(detail.getNbRep());
					serie.setEntrainement(entrainement);
					serie.setExercice(exercice);
					serie.setSeance(seance);
					serie = this.serieService.save(serie);
					if(ObjectUtils.isEmpty(detailExercice)) {
						detailExercice = new DetailsExercice();
						detailExercice.setEntrainement(entrainement);
						detailExercice.setExercice(exercice);
					}
					detailExercice.setNbSerie(detail.getNbSerie());
					this.detailsRepository.save(detailExercice);
					
					if(CollectionUtils.containsInstance(series, serie)) {
						series.remove(serie);
					}
					if(CollectionUtils.containsInstance(detailsExercices, detailExercice)) {
						detailsExercices.remove(detailExercice);
					}
				}
			
			
			// Suppression des series / exercices qui ne sont plus dans la séance d'entrainement
			this.serieService.deleteByIds(series.stream().map(Serie::getId).collect(Collectors.toList()));
			this.detailsRepository.deleteAllById(detailsExercices.stream().map(DetailsExercice::getId).collect(Collectors.toList()));
			}
		}
				
		
		return entrainement;
	}

}
