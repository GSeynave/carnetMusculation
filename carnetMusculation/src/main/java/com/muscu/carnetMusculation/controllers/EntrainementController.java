package com.muscu.carnetMusculation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.muscu.carnetMusculation.dto.EntrainementAPI;
import com.muscu.carnetMusculation.dto.MapperAPI;
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

}
