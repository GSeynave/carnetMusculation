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
import com.muscu.carnetMusculation.dto.SeanceAPI;
import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.services.SeanceService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders={"*"})
@RequestMapping("/seances")
public class SeanceController {
	Logger logger = LoggerFactory.getLogger(SeanceController.class);

	@Autowired
	private SeanceService seanceService;

	@Autowired
	private MapperAPI mapperApi;

	@GetMapping("")
	public @ResponseBody ResponseEntity<List<SeanceAPI>> getAll() {
		System.out.println("controler sessions");
		List<Seance> sessions = seanceService.getAll();
		return new ResponseEntity<List<SeanceAPI>>(
				sessions.stream()
				.map(mapperApi::convertToDto)
				.collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<SeanceAPI> getById(@PathVariable Long id) {
		return new ResponseEntity<SeanceAPI>(mapperApi.convertToDto(seanceService.findById(id)), HttpStatus.OK);
	}

	@GetMapping("/programme/{id}")
	public @ResponseBody ResponseEntity<List<SeanceAPI>> getByProgrammeId(@PathVariable Long id) {

		List<Seance> sessions = seanceService.findByProgrammeId(id);
		return new ResponseEntity<List<SeanceAPI>>(
				sessions.stream()
				.map(mapperApi::convertToDto)
				.collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@PostMapping("")
	public @ResponseBody ResponseEntity<SeanceAPI> save(@RequestBody SeanceAPI seanceApi) {
		logger.trace("Saving sessionApi {}", seanceApi);
		seanceService.save(mapperApi.convertToEntity(seanceApi));
		return new ResponseEntity<SeanceAPI>(seanceApi , HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<SeanceAPI> delete(@PathVariable Long id) {
		System.out.println("delete id :" +id);
		Seance seance = seanceService.findById(id);
		if(!Objects.isNull(seance)) {
			seanceService.delete(seance);
			return new ResponseEntity<SeanceAPI>(mapperApi.convertToDto(seance), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<SeanceAPI>(mapperApi.convertToDto(seance), HttpStatus.NOT_FOUND);
		}
	}

}
