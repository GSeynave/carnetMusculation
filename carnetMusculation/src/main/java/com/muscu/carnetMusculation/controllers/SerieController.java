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

import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.dto.SerieAPI;
import com.muscu.carnetMusculation.dto.SerieLineAPI;
import com.muscu.carnetMusculation.entities.Serie;
import com.muscu.carnetMusculation.services.SerieService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders={"*"})
@RequestMapping("/series")
public class SerieController {
	Logger logger = LoggerFactory.getLogger(SerieController.class);

	@Autowired
	private SerieService serieService;

	@Autowired
	private MapperAPI mapperApi;

	@GetMapping("")
	public @ResponseBody ResponseEntity<List<SerieAPI>> getAll() {
		List<Serie> series = serieService.getAll();
		return new ResponseEntity<List<SerieAPI>>(
				series.stream()
				.map(mapperApi::convertToDto)
				.collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<SerieAPI> getById(@PathVariable Long id) {
		return new ResponseEntity<SerieAPI>(mapperApi.convertToDto(serieService.findById(id)), HttpStatus.OK);
	}

	@GetMapping("/exercice/{id}")
	public @ResponseBody ResponseEntity<List<SerieLineAPI>> getByExerciceId(@PathVariable Long id) {
		return new ResponseEntity<List<SerieLineAPI>>(serieService.getSerieLineAPIByExerciceId(id), HttpStatus.OK);
	}

	@PostMapping("")
	public @ResponseBody ResponseEntity<SerieAPI> save(@RequestBody SerieAPI serieApi) {
		logger.trace("Saving sessionApi {}", serieApi);
		serieService.save(mapperApi.convertToEntity(serieApi));
		return new ResponseEntity<SerieAPI>(serieApi , HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<SerieAPI> delete(@PathVariable Long id) {
		System.out.println("delete id :" +id);
		Serie serie = serieService.findById(id);
		if(!Objects.isNull(serie)) {
			serieService.delete(serie);
			return new ResponseEntity<SerieAPI>(mapperApi.convertToDto(serie), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<SerieAPI>(mapperApi.convertToDto(serie), HttpStatus.NOT_FOUND);
		}
	}

}
