package com.muscu.carnetMusculation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.muscu.carnetMusculation.dto.DetailsExerciceAPI;
import com.muscu.carnetMusculation.dto.EntrainementAPI;
import com.muscu.carnetMusculation.dto.EntrainementCreerAPI;
import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.entities.DetailsExercice;
import com.muscu.carnetMusculation.entities.Entrainement;
import com.muscu.carnetMusculation.services.IEntrainementService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders={"*"})
@RequestMapping("/entrainements")
public class EntrainementController {
	Logger LOGGER = LoggerFactory.getLogger(EntrainementController.class);

	@Autowired
	private IEntrainementService entrainementService;

	@Autowired
	private MapperAPI mapperApi;

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<EntrainementAPI> finbById(@PathVariable(name = "id") Long entrainementId) {
		LOGGER.debug("Recherche d'entrainement avec l'id {}", entrainementId);
		Entrainement entrainement = entrainementService.findById(entrainementId);
		return new ResponseEntity<EntrainementAPI>(mapperApi.convertToDto(entrainement), HttpStatus.OK);
	}
	
	@GetMapping("/programme/{id}")
	public @ResponseBody ResponseEntity<List<EntrainementAPI>> findByProgrammeId(@PathVariable(name = "id") Long programmeId) {
		List<Entrainement> entrainements = entrainementService.findByProgrammeId(programmeId);
		LOGGER.debug("nb program returned {}", entrainements.size());
		return new ResponseEntity<List<EntrainementAPI>>(
				entrainements.stream()
					.map(mapperApi::convertToDto)
					.collect(Collectors.toList()),
				HttpStatus.OK);
	}
	
	@GetMapping("/{entrainementId}/details/{exerciceId}")
	public @ResponseBody ResponseEntity<DetailsExerciceAPI> findDetailsByEntrainementIdAndExerciceId(
			@PathVariable(name = "entrainementId") Long entrainementId,
			@PathVariable(name = "exerciceId") Long exerciceId) {
		LOGGER.debug("Recherche details exercice id {} et entrainement id {}", entrainementId, exerciceId);
		DetailsExercice details = entrainementService.findDetailsByEntrainementIdAndExerciceId(exerciceId, entrainementId);
		return new ResponseEntity<DetailsExerciceAPI>(mapperApi.convertToDto(details), HttpStatus.OK);
	}
	
	@PostMapping()
	public @ResponseBody ResponseEntity<EntrainementAPI> save(@RequestBody EntrainementCreerAPI entrainementCreerApi){
		return new ResponseEntity<EntrainementAPI>(entrainementService.creationEntrainement(entrainementCreerApi), HttpStatus.OK);
	}

	@PostMapping("/update")
	public @ResponseBody ResponseEntity<EntrainementAPI> update(@RequestBody EntrainementCreerAPI entrainementCreerApi){
		return new ResponseEntity<EntrainementAPI>(mapperApi.convertToDto(entrainementService.update(entrainementCreerApi)), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Long> delete(@PathVariable Long id){
		if(this.entrainementService.existsById(id)) {
			this.entrainementService.deleteById(id);
			return new ResponseEntity<Long>(id, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}
