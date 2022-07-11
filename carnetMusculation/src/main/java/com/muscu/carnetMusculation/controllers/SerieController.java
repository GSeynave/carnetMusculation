package com.muscu.carnetMusculation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.dto.SerieAPI;
import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.entities.Serie;
import com.muscu.carnetMusculation.services.impl.SeanceService;
import com.muscu.carnetMusculation.services.impl.SerieService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders={"*"})
@RequestMapping("/series")
public class SerieController {
	Logger LOGGER = LoggerFactory.getLogger(EntrainementController.class);


	private final SerieService serieService;
	private final MapperAPI mapperApi;
	private final SeanceService seanceService;

	public SerieController(SerieService serieService, SeanceService seanceService, MapperAPI mapperApi) {
		super();
		this.serieService = serieService;
		this.mapperApi = mapperApi;
		this.seanceService = seanceService;
	}
	
	@PostMapping("/seance/{seanceId}")
	public @ResponseBody ResponseEntity<SerieAPI> save(@PathVariable(name = "seanceId") long seanceId, @RequestBody SerieAPI serieApi) {
		try {
			LOGGER.info("Ajout de la série avec l'id {}", serieApi.getId());
			Serie serie = mapperApi.convertToEntity(serieApi);
			Seance seance = seanceService.findById(seanceId);
			serie.setSeance(seance);
			serieApi = mapperApi.convertToDto(serieService.save(serie)); 
			return new ResponseEntity<SerieAPI>(serieApi, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur pendant la récupération de la seance", e);
		}
	}
}
