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
import com.muscu.carnetMusculation.dto.ProgrammeAPI;
import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.services.ProgramService;
import com.muscu.carnetMusculation.services.SeanceService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders={"*"})
@RequestMapping("/programs")
public class ProgramController {
	Logger logger = LoggerFactory.getLogger(ProgramController.class);

	@Autowired
	private ProgramService programService;

	@Autowired
	private MapperAPI mapperApi;

	@GetMapping("")
	public @ResponseBody ResponseEntity<List<ProgrammeAPI>> getAll() {
		List<Programme> programs = programService.getAll();
		return new ResponseEntity<List<ProgrammeAPI>>(
				programs.stream()
				.map(mapperApi::convertToDto)
				.collect(Collectors.toList()),
				HttpStatus.OK);
	}


	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<ProgrammeAPI> getById(@PathVariable Long id) {
		return new ResponseEntity<ProgrammeAPI>(mapperApi.convertToDto(programService.findById(id)), HttpStatus.OK);
	}

	@PostMapping("")
	public @ResponseBody ResponseEntity<ProgrammeAPI> save(@RequestBody ProgrammeAPI programApi) {
		logger.trace("Saving programApi {}", programApi);
		Programme program = mapperApi.convertToEntity(programApi);
		programService.save(program);
		return new ResponseEntity<ProgrammeAPI>(programApi , HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<ProgrammeAPI> delete(@PathVariable Long id) {
		System.out.println("delete id :" +id);
		Programme program = programService.findById(id);
		if(!Objects.isNull(program)) {
			programService.delete(program);
			return new ResponseEntity<ProgrammeAPI>(mapperApi.convertToDto(program), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<ProgrammeAPI>(mapperApi.convertToDto(program), HttpStatus.NOT_FOUND);
		}
	}

}
