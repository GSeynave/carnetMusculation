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
import com.muscu.carnetMusculation.services.IProgrammeService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders={"*"})
@RequestMapping("/programmes")
public class ProgrammeController {
	Logger LOGGER = LoggerFactory.getLogger(ProgrammeController.class);

	@Autowired
	private IProgrammeService programmeService;

	@Autowired
	private MapperAPI mapperApi;

	@GetMapping("")
	public @ResponseBody ResponseEntity<List<ProgrammeAPI>> findAll() {
		List<Programme> programmes = programmeService.findAll();
		LOGGER.debug("nb program returned {}", programmes.size());
		return new ResponseEntity<List<ProgrammeAPI>>(
				programmes.stream()
					.map(mapperApi::convertToDto)
					.collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<ProgrammeAPI> findById(@PathVariable Long id) {
		Programme programme = programmeService.findById(id);
		ProgrammeAPI programmeAPI = mapperApi.convertToDto(programme);
		return new ResponseEntity<ProgrammeAPI>(programmeAPI, HttpStatus.OK);
	}

	@PostMapping("")
	public @ResponseBody ResponseEntity<ProgrammeAPI> save(@RequestBody ProgrammeAPI programApi) {
		LOGGER.debug("Saving programApi {}", programApi);
		Programme program = mapperApi.convertToEntity(programApi);
		programmeService.save(program);
		return new ResponseEntity<ProgrammeAPI>(programApi , HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> deleteById(@PathVariable Long id) {
		System.out.println("delete id :" +id);
		if(programmeService.existsById(id)) {
			programmeService.deleteById(id);
			return new ResponseEntity<String>("Le programme a été supprimé", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Le programme n'existe pas", HttpStatus.NOT_FOUND);
		}
	}

}
