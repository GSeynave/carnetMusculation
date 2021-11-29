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
import com.muscu.carnetMusculation.dto.SessionAPI;
import com.muscu.carnetMusculation.entities.Session;
import com.muscu.carnetMusculation.services.SessionService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders={"*"})
@RequestMapping("/sessions")
public class SessionController {
	Logger logger = LoggerFactory.getLogger(SessionController.class);

	@Autowired
	private SessionService sessionService;

	private MapperAPI mapperApi;

	@GetMapping("")
	public @ResponseBody ResponseEntity<List<SessionAPI>> getAll() {
		System.out.println("controler sessions");
		List<Session> sessions = sessionService.getAll();
		return new ResponseEntity<List<SessionAPI>>(
				sessions.stream()
				.map(mapperApi::convertToDto)
				.collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<SessionAPI> getById(@PathVariable Long id) {
		return new ResponseEntity<SessionAPI>(mapperApi.convertToDto(sessionService.findById(id)), HttpStatus.OK);
	}

	@PostMapping("")
	public @ResponseBody ResponseEntity<SessionAPI> save(@RequestBody SessionAPI sessionApi) {
		logger.trace("Saving sessionApi {}", sessionApi);
		sessionService.save(mapperApi.convertToEntity(sessionApi));
		return new ResponseEntity<SessionAPI>(sessionApi , HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<SessionAPI> delete(@PathVariable Long id) {
		System.out.println("delete id :" +id);
		Session session = sessionService.findById(id);
		if(!Objects.isNull(session)) {
			sessionService.delete(session);
			return new ResponseEntity<SessionAPI>(mapperApi.convertToDto(session), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<SessionAPI>(mapperApi.convertToDto(session), HttpStatus.NOT_FOUND);
		}
	}

}
