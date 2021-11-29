package com.muscu.carnetMusculation.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
import com.muscu.carnetMusculation.dto.ProgramAPI;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.entities.Program;
import com.muscu.carnetMusculation.services.ExerciceService;
import com.muscu.carnetMusculation.services.ProgramService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders={"*"})
@RequestMapping("/programs")
public class ProgramController {
	Logger logger = LoggerFactory.getLogger(ProgramController.class);

	@Autowired
	private ProgramService programService;

	@Autowired
	private ExerciceService exerciceService;

	@Autowired
	private MapperAPI mapperApi;

	@GetMapping("")
	public @ResponseBody ResponseEntity<List<ProgramAPI>> getAll() {
		List<Program> programs = programService.getAll();
		return new ResponseEntity<List<ProgramAPI>>(
				programs.stream()
				.map(mapperApi::convertToDto)
				.collect(Collectors.toList()),
				HttpStatus.OK);
	}


	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<ProgramAPI> getById(@PathVariable Long id) {
		return new ResponseEntity<ProgramAPI>(mapperApi.convertToDto(programService.findById(id)), HttpStatus.OK);
	}

	@PostMapping("")
	public @ResponseBody ResponseEntity<ProgramAPI> save(@RequestBody ProgramAPI programApi) {
		logger.trace("Saving programApi {}", programApi);
		Set<Exercice> exercices = new HashSet<Exercice>();
		Program program = mapperApi.convertToEntity(programApi);
		if(!programApi.getExercices().isEmpty()) {
			for (ExerciceAPI exerciceApi : programApi.getExercices()) {
				exercices.add(exerciceService.findById(exerciceApi.getId()));
			}
			program.setExercices(exercices);
		}
		programService.save(program);
		return new ResponseEntity<ProgramAPI>(programApi , HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<ProgramAPI> delete(@PathVariable Long id) {
		System.out.println("delete id :" +id);
		Program program = programService.findById(id);
		if(!Objects.isNull(program)) {
			programService.delete(program);
			return new ResponseEntity<ProgramAPI>(mapperApi.convertToDto(program), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<ProgramAPI>(mapperApi.convertToDto(program), HttpStatus.NOT_FOUND);
		}
	}

}
