package com.muscu.carnetMusculation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.muscu.carnetMusculation.dto.SeanceInformationInit;
import com.muscu.carnetMusculation.services.impl.SeanceService;
import com.muscu.carnetMusculation.utils.SeanceState;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = { "*" })
@RequestMapping(value = "/seances")
public class SeanceController {
	Logger LOGGER = LoggerFactory.getLogger(EntrainementController.class);

	private final SeanceService seanceService;

	public SeanceController(SeanceService seanceService) {
		super();
		this.seanceService = seanceService;
	}


	@GetMapping("/entrainement/{entrainementId}/{state}")
	public @ResponseBody ResponseEntity<SeanceInformationInit> findByEntrainementIdAndState(
			@PathVariable(name = "entrainementId") Long entrainementId, @PathVariable(name = "state") int state) {
		try {
			LOGGER.debug("Recherche des séance pour l'entrainement avec l'id {} et l'état {}", entrainementId, state);
			SeanceInformationInit sii = seanceService.findSIIByEntrainementIdAndState(entrainementId,
					SeanceState.getSeanceStateByCode(state));
			return new ResponseEntity<SeanceInformationInit>(sii, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur pendant la récupération de la seance", e);
		}
	}
}
