package com.muscu.carnetMusculation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.dto.SeanceAPI;
import com.muscu.carnetMusculation.dto.SeanceInformationInit;
import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.services.impl.SeanceService;
import com.muscu.carnetMusculation.utils.SeanceState;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/seances")
public class SeanceController {
	Logger LOGGER = LoggerFactory.getLogger(EntrainementController.class);

	private final SeanceService seanceService;
	private final MapperAPI mapperApi;

	public SeanceController(SeanceService seanceService, MapperAPI mapperApi) {
		super();
		this.seanceService = seanceService;
		this.mapperApi = mapperApi;
	}


	@GetMapping("/entrainement/{entrainementId}/state/{state}")
	public @ResponseBody ResponseEntity<SeanceInformationInit> findByEntrainementIdAndState(
			@PathVariable(name = "entrainementId") Long entrainementId, @PathVariable(name = "state") int state) {
		try {
			LOGGER.info("Recherche des séance pour l'entrainement avec l'id {} et l'état {}", entrainementId, state);
			SeanceInformationInit sii = seanceService.findSIIByEntrainementIdAndState(entrainementId,
					SeanceState.getSeanceStateByCode(state));
			return new ResponseEntity<SeanceInformationInit>(sii, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur pendant la récupération de la seance", e);
		}
	}
	
	@GetMapping("/programme/{programmeId}/entrainement/{entrainementId}/state/{state}")
	public @ResponseBody ResponseEntity<SeanceInformationInit> findByProgrammeIdAndEntrainementIdAndState(
			@PathVariable(name = "programmeId") Long programmeId,
			@PathVariable(name = "entrainementId") Long entrainementId,
			@PathVariable(name = "state") int state) {
		try {
			LOGGER.info("Recherche des séance pour l'entrainement avec l'id {} et l'état {}", entrainementId, state);
			SeanceInformationInit sii = seanceService.findSIIByProgrammeIdAndEntrainementIdAndState(programmeId, entrainementId, SeanceState.getSeanceStateByCode(state));
			return new ResponseEntity<SeanceInformationInit>(sii, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur pendant la récupération de la seance", e);
		}
	}
	
	@PostMapping("/entrainement/{entrainementId}/status/{state}")
	public @ResponseBody ResponseEntity<SeanceAPI> save(
			@PathVariable(name = "entrainementId") long entrainementId, 
			@PathVariable(name = "state") int state) {
		try {
			LOGGER.info("Création de la séance avec l'entrainement id {} et l'état {}", entrainementId, state);
			SeanceAPI seanceApi = mapperApi.convertToDto(seanceService.createSeance(entrainementId, SeanceState.getSeanceStateByCode(state)));
			return new ResponseEntity<SeanceAPI>(seanceApi, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur pendant la récupération de la seance", e);
		}
	}
	
	@PostMapping("/{seanceId}")
	public @ResponseBody ResponseEntity<SeanceAPI> setEntrainementState(@PathVariable(name = "seanceId") long seanceId, @RequestBody SeanceState state) {
		try {
			LOGGER.info("Mise à jour de l'état de la séance avec l'id {} et l'état {}", seanceId, state);
			Seance seance = seanceService.findById(seanceId);
			seance.setState(SeanceState.getSeanceStateByCode(state.getCode()));
			SeanceAPI seanceApi = mapperApi.convertToDto(seanceService.save(seance));
			return new ResponseEntity<SeanceAPI>(seanceApi, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur pendant la récupération de la seance", e);
		}
	}
}
