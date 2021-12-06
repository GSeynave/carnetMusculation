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
import com.muscu.carnetMusculation.dto.ExerciceAPI;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.services.ExerciceService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders={"*"})
@RequestMapping("/exercices")
public class ExerciceController {
	Logger logger = LoggerFactory.getLogger(ExerciceController.class);

	@Autowired
	ExerciceService exerciceService;

	@Autowired
	private MapperAPI mapperApi;

	@GetMapping("")
	public @ResponseBody ResponseEntity<List<ExerciceAPI>> getAll() {
		System.out.println("controler exercices");
		List<Exercice> exercices = exerciceService.getAll();
		return new ResponseEntity<List<ExerciceAPI>>(
				exercices.stream()
				.map(mapperApi::convertToDto)
				.collect(Collectors.toList()),
				HttpStatus.OK);
	}


	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<ExerciceAPI> getById(@PathVariable Long id) {
		return new ResponseEntity<ExerciceAPI>(mapperApi.convertToDto(exerciceService.findById(id)), HttpStatus.OK);
	}
	
	@GetMapping("/seance/{id}")
	public @ResponseBody ResponseEntity<List<ExerciceAPI>> getBySeances(@PathVariable Long id) {
		List<Exercice> exercices = exerciceService.findBySeances(id);
		return new ResponseEntity<List<ExerciceAPI>>(
				exercices.stream()
				.map(mapperApi::convertToDto)
				.collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@PostMapping("")
	public @ResponseBody ResponseEntity<ExerciceAPI> save(@RequestBody ExerciceAPI exerciceApi) {
		logger.trace("Saving exerciceApi {}", exerciceApi);
		exerciceService.save(mapperApi.convertToEntity(exerciceApi));
		return new ResponseEntity<ExerciceAPI>(exerciceApi , HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<ExerciceAPI> delete(@PathVariable Long id) {
		System.out.println("delete id :" +id);
		Exercice exercice = exerciceService.findById(id);
		if(!Objects.isNull(exercice)) {
			exerciceService.delete(exercice);
			return new ResponseEntity<ExerciceAPI>(mapperApi.convertToDto(exercice), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<ExerciceAPI>(mapperApi.convertToDto(exercice), HttpStatus.NOT_FOUND);
		}
	}

}
