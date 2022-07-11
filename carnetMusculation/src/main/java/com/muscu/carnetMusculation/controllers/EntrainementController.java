package com.muscu.carnetMusculation.controllers;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.muscu.carnetMusculation.dto.DetailsExerciceAPI;
import com.muscu.carnetMusculation.dto.EntrainementAPI;
import com.muscu.carnetMusculation.dto.EntrainementCreerAPI;
import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.entities.Entrainement;
import com.muscu.carnetMusculation.entities.EntrainementExercice;
import com.muscu.carnetMusculation.services.impl.EntrainementService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/entrainements")
public class EntrainementController {
	Logger LOGGER = LoggerFactory.getLogger(EntrainementController.class);

	private final EntrainementService entrainementService;
	private final MapperAPI mapperApi;

	public EntrainementController(EntrainementService entrainementService, MapperAPI mapperApi) {
		super();
		this.entrainementService = entrainementService;
		this.mapperApi = mapperApi;
	}

	@GetMapping("/{id}")

	public @ResponseBody ResponseEntity<EntrainementAPI> finbById(@PathVariable(name = "id") Long entrainementId) {
		try {
			LOGGER.info("Recherche d'entrainement avec l'id {}", entrainementId);
			Entrainement entrainement = entrainementService.findById(entrainementId);
			System.out.println("type ent: " +entrainement.getType());
			return new ResponseEntity<EntrainementAPI>(mapperApi.convertToDto(entrainement), HttpStatus.OK);
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun entrainement", e);
		}
	}

	@GetMapping("/programme/{id}")
	public @ResponseBody ResponseEntity<List<EntrainementAPI>> findByProgrammeId(
			@PathVariable(name = "id") Long programmeId) {
		try {

			List<Entrainement> entrainements = entrainementService.findByProgrammeId(programmeId);
			LOGGER.info("nb program returned {}", entrainements.size());
			if(!CollectionUtils.isEmpty(entrainements)) {
				return new ResponseEntity<List<EntrainementAPI>>(
						entrainements.stream().map(mapperApi::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
			} else {
				throw new EntityNotFoundException();
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur pendant la récupératino de la liste des entrainements", e);
		}
	}

	@GetMapping("/{entrainementId}/details/{exerciceId}")
	public @ResponseBody ResponseEntity<DetailsExerciceAPI> findDetailsByEntrainementIdAndExerciceId(
			@PathVariable(name = "entrainementId") Long entrainementId,
			@PathVariable(name = "exerciceId") Long exerciceId) {
		try {
			LOGGER.info("Recherche details exercice id {} et entrainement id {}", entrainementId, exerciceId);
			EntrainementExercice details = entrainementService.findDetailsByEntrainementIdAndExerciceId(entrainementId,
					exerciceId);
			return new ResponseEntity<DetailsExerciceAPI>(mapperApi.convertToDto(details), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur pendant la récupération des details", e);
		}
	}

	@GetMapping("/{entrainementId}/details")
	public @ResponseBody ResponseEntity<EntrainementCreerAPI> findSeanceInformationInitByEntrainementId(
			@PathVariable(name = "entrainementId") Long entrainementId) {
		try {

			LOGGER.info("Recherche seance information init avec entrainement id {}", entrainementId);
			return new ResponseEntity<EntrainementCreerAPI>(
					entrainementService.findSeanceInformationInitByEntrainementId(entrainementId), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur pendant la récupération des details", e);
		}
	}

	@PostMapping()
	public @ResponseBody ResponseEntity<EntrainementCreerAPI> save(
			@RequestBody EntrainementCreerAPI entrainementCreerApi) throws ParseException {
		try {
			return new ResponseEntity<EntrainementCreerAPI>(
					entrainementService.creationEntrainement(entrainementCreerApi), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Erreur la sauvegarde de l'entrainement", e);
		}
	}

	@PatchMapping
	public @ResponseBody ResponseEntity<EntrainementCreerAPI> update(
			@RequestBody EntrainementCreerAPI entrainementCreerApi) throws ParseException {
		try {
			return new ResponseEntity<EntrainementCreerAPI>(
					entrainementService.modificationEntrainement(entrainementCreerApi), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Erreur la sauvegarde de l'entrainement", e);
		}
			
	}
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Long> delete(@PathVariable Long id) {
		try {

			if (this.entrainementService.existsById(id)) {
				this.entrainementService.deleteById(id);
				return new ResponseEntity<Long>(id, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Erreur pendant la suppression", e);
		}
	}

}
