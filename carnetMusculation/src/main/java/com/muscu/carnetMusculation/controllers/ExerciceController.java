package com.muscu.carnetMusculation.controllers;

import java.util.List;
import java.util.stream.Collectors;

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

import com.muscu.carnetMusculation.dto.DetailsExerciceAPI;
import com.muscu.carnetMusculation.dto.ExerciceAPI;
import com.muscu.carnetMusculation.dto.ExercicePerformance;
import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.entities.EntrainementExercice;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.services.impl.ExerciceService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = { "*" })
@RequestMapping("/exercices")
public class ExerciceController {

	Logger LOGGER = LoggerFactory.getLogger(ExerciceController.class);

	private final ExerciceService exerciceService;
	private final MapperAPI mapperApi;

	public ExerciceController(ExerciceService exerciceService, MapperAPI mapperApi) {
		super();
		this.exerciceService = exerciceService;
		this.mapperApi = mapperApi;
	}

	@GetMapping("/entrainement/{entrainementId}")
	public @ResponseBody ResponseEntity<List<DetailsExerciceAPI>> findByEntrainementId(
			@PathVariable(name = "entrainementId") Long entrainementId) {
		try {
			List<EntrainementExercice> details = this.exerciceService.findByEntrainementId(entrainementId);

			return new ResponseEntity<List<DetailsExerciceAPI>>(
					details.stream().map(mapperApi::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Erreur pendant la récupératino de la liste des exercices", e);
		}
	}
	
	@GetMapping("/seance/{seanceId}")
	public @ResponseBody ResponseEntity<List<ExercicePerformance>> findBySeanceId(
			@PathVariable(name = "seanceId") Long seanceId) {
		try {
			List<ExercicePerformance> exercicePerformances = this.exerciceService.findBySeanceId(seanceId);

			return new ResponseEntity<List<ExercicePerformance>>(exercicePerformances, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Erreur pendant la récupératino de la liste des exercices", e);
		}
	}

	@GetMapping("")
	public @ResponseBody ResponseEntity<List<ExerciceAPI>> findAll() {
		try {
			List<Exercice> exercices = this.exerciceService.findAll();
			return new ResponseEntity<List<ExerciceAPI>>(
					exercices.stream().map(mapperApi::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Erreur pendant la récupératino de la liste des exercices", e);
		}
	}
}
