package com.muscu.carnetMusculation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.dto.ProgrammeAPI;
import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.services.impl.ProgrammeService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/programmes")
public class ProgrammeController {
	Logger LOGGER = LoggerFactory.getLogger(ProgrammeController.class);

	private final ProgrammeService programmeService;
	private final MapperAPI mapperApi;

	public ProgrammeController(ProgrammeService programmeService, MapperAPI mapperApi) {
		super();
		this.programmeService = programmeService;
		this.mapperApi = mapperApi;
	}

	@GetMapping(params = { "page", "size", "sort" })
	public @ResponseBody ResponseEntity<List<ProgrammeAPI>> findPaginated(@RequestParam("page") String page,
			@RequestParam("size") String size, @RequestParam("sort") String sort) {
		try {
			List<Programme> programmes = programmeService.findPaginated(Integer.valueOf(page), Integer.valueOf(size),
					sort);
			return new ResponseEntity<List<ProgrammeAPI>>(
					programmes.stream().map(mapperApi::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Erreur pendant la récupération de la liste des programmes", e);
		}
	}

	@GetMapping()
	public @ResponseBody ResponseEntity<List<ProgrammeAPI>> findAll() {
		try {
			List<Programme> programmes = programmeService.findAll();
			return new ResponseEntity<List<ProgrammeAPI>>(
					programmes.stream().map(mapperApi::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun programme", e);
		}
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<ProgrammeAPI> findById(@PathVariable Long id) {
		try {
			Programme programme = programmeService.findById(id);
			ProgrammeAPI programmeAPI = mapperApi.convertToDto(programme);
			return new ResponseEntity<ProgrammeAPI>(programmeAPI, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur pendant la récupération du programme", e);
		}
	}

	@PostMapping("")
	public @ResponseBody ResponseEntity<ProgrammeAPI> save(@RequestBody ProgrammeAPI programApi) {
		try {
			LOGGER.debug("Saving programApi {}", programApi);
			Programme program = mapperApi.convertToEntity(programApi);
			programApi = mapperApi.convertToDto(this.programmeService.save(program));
			return new ResponseEntity<ProgrammeAPI>(programApi, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur pendant la sauvegarde du programme", e);
		}
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Long> deleteById(@PathVariable Long id) {
		try {
			if (!ObjectUtils.isEmpty(this.programmeService.findById(id))) {
				programmeService.deleteById(id);
				return new ResponseEntity<Long>(id, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur pendant la suppression du programme", e);
		}
	}

	@GetMapping("/count")
	public @ResponseBody ResponseEntity<Long> countAll() {
		try {
			LOGGER.debug("count all");
			return new ResponseEntity<Long>(programmeService.countAll(), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Erreur pendant la récupération du nombre de programmes", e);
		}
	}

}
