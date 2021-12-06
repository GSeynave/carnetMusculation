package com.muscu.carnetMusculation.controllers;

import java.util.List;
import java.util.Objects;
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

import com.muscu.carnetMusculation.dto.EntrainementAPI;
import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.dto.SeanceAPI;
import com.muscu.carnetMusculation.dto.SerieAPI;
import com.muscu.carnetMusculation.entities.Entrainement;
import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.entities.Serie;
import com.muscu.carnetMusculation.services.EntrainementService;
import com.muscu.carnetMusculation.services.SerieService;
import com.muscu.carnetMusculation.services.SeanceService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders={"*"})
@RequestMapping("/entrainements")
public class EntrainementController {
	Logger logger = LoggerFactory.getLogger(EntrainementController.class);

	@Autowired
	private EntrainementService entrainementService;

	@Autowired
	private MapperAPI mapperApi;

	@GetMapping("")
	public @ResponseBody ResponseEntity<List<EntrainementAPI>> getAll() {
		System.out.println("controler sessions");
		List<Entrainement> entrainements = entrainementService.getAll();
		return new ResponseEntity<List<EntrainementAPI>>(
				entrainements.stream()
				.map(mapperApi::convertToDto)
				.collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<EntrainementAPI> getById(@PathVariable Long id) {
		return new ResponseEntity<EntrainementAPI>(mapperApi.convertToDto(entrainementService.findById(id)), HttpStatus.OK);
	}

	@PostMapping("")
	public @ResponseBody ResponseEntity<EntrainementAPI> save(@RequestBody EntrainementAPI entrainementApi) {
		logger.trace("Saving sessionApi {}", entrainementApi);
		entrainementService.save(mapperApi.convertToEntity(entrainementApi));
		return new ResponseEntity<EntrainementAPI>(entrainementApi , HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<EntrainementAPI> delete(@PathVariable Long id) {
		System.out.println("delete id :" +id);
		Entrainement entrainement = entrainementService.findById(id);
		if(!Objects.isNull(entrainement)) {
			entrainementService.delete(entrainement);
			return new ResponseEntity<EntrainementAPI>(mapperApi.convertToDto(entrainement), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<EntrainementAPI>(mapperApi.convertToDto(entrainement), HttpStatus.NOT_FOUND);
		}
	}

}
