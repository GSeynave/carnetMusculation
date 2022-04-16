package com.muscu.carnetMusculation.controller;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.muscu.carnetMusculation.CarnetMusculationApplication;
import com.muscu.carnetMusculation.SQLConfig;
import com.muscu.carnetMusculation.controllers.ProgrammeController;
import com.muscu.carnetMusculation.dto.ProgrammeAPI;
import com.muscu.carnetMusculation.entities.Programme;

@SpringBootTest(classes = { CarnetMusculationApplication.class, SQLConfig.class })
public class ProgrammeControllerTest {
	@Autowired
	private ProgrammeController programmeController;


	@Test
	public void findByIdTest() {
		Long id = 1l;

		ResponseEntity<ProgrammeAPI> responseEntityApi = programmeController.findById(id);
		ProgrammeAPI programmeApi = responseEntityApi.getBody();

		Assertions.assertNotNull(programmeApi);
		Assertions.assertEquals(id, programmeApi.getId());
	}

//	@Test
//	public void deleteByIdTest() {
//		Long id = 10l;
//		Programme programme = programmeBuilder();
//		programme.setId(id);
//		programmeService.save(programme);
//
//		programmeController.deleteById(id);
//
//		ResponseEntity<List<ProgrammeAPI>> responseEntityApi = programmeController.findAll();
//		List<ProgrammeAPI> programmesApi = responseEntityApi.getBody();
//
//		Assertions.assertFalse(
//				programmesApi.stream().map(ProgrammeAPI::getId).filter(id::equals).findFirst().isPresent());
//	}

	public Programme programmeBuilder() {
		Programme programme = new Programme();
		programme.setNom("programme" + (int) Math.floor(Math.random() * (100 - 1 + 1) + 1));
		programme.setDateCreation(new Date(System.currentTimeMillis()));
		return programme;
	}
}