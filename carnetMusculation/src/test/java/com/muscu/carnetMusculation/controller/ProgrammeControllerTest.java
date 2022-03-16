package com.muscu.carnetMusculation.controller;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import com.muscu.carnetMusculation.CarnetMusculationApplication;
import com.muscu.carnetMusculation.SQLConfig;
import com.muscu.carnetMusculation.controllers.ProgrammeController;
import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.dto.ProgrammeAPI;
import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.services.IProgrammeService;


@SpringBootTest(classes = {CarnetMusculationApplication.class, SQLConfig.class})
public class ProgrammeControllerTest {
	@Autowired
	private ProgrammeController programmeController;
	@Autowired
	private IProgrammeService programmeService;

	@Autowired
	private MapperAPI mapperApi;
	
	
	@Test
	public void findAllTest() {
		List<Programme> programmes = new ArrayList<Programme>();
		
		for(int i = 0; i < 5; i++) {
			programmes.add(programmeBuilder());
		}
		for (Programme programme : programmes) {
			programmeController.save(mapperApi.convertToDto(programme));
		}
		ResponseEntity<List<ProgrammeAPI>> responseEntity = programmeController.findAll();
		List<ProgrammeAPI> programmeApiList = responseEntity.getBody();
		
		Assertions.assertEquals(5, programmeApiList.size());
		

		for (ProgrammeAPI programmeAPI : programmeApiList) {
			programmeController.deleteById(programmeAPI.getId());
		}
	}
	
	@Test
	public void findByIdTest() {
		Long id = 1l;
		Programme programme = programmeBuilder();
		programme.setId(id);
		programmeService.save(programme);

		ResponseEntity<ProgrammeAPI> responseEntityApi = programmeController.findById(id);
		ProgrammeAPI programmeApi = responseEntityApi.getBody();
		
		Assertions.assertNotNull(programmeApi);
		
		programmeService.deleteById(programme.getId());
	}
	
	@Test
	public void deleteByIdTest() {
		Long id = 1l;
		Programme programme = programmeBuilder();
		programme.setId(id);
		programmeService.save(programme);

		programmeController.deleteById(id);

		ResponseEntity<List<ProgrammeAPI>> responseEntityApi = programmeController.findAll();
		List<ProgrammeAPI> programmesApi = responseEntityApi.getBody();
		
		Assertions.assertFalse(programmesApi.stream()
				.map(ProgrammeAPI::getId)
				.filter(id::equals)
				.findFirst()
				.isPresent());
	}
	
	public Programme programmeBuilder() {
		Programme programme = new Programme();
		programme.setNom("programme"+(int)Math.floor(Math.random()*(100-1+1)+1));
		programme.setDateCreation(new Date(System.currentTimeMillis()));
		return programme;
	}
}