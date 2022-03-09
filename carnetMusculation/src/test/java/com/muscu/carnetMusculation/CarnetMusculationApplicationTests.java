package com.muscu.carnetMusculation;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.muscu.carnetMusculation.controllers.ExerciceController;
import com.muscu.carnetMusculation.dto.ExerciceAPI;
import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.services.ExerciceService;

@SpringBootTest()
@TestInstance(Lifecycle.PER_CLASS)
class CarnetMusculationApplicationTests {
	@Autowired
	private MapperAPI mapperApi;
	
	@Autowired
	private ExerciceController exerciceController;
	
	@Test
	void contextLoads() {
	}
	
	@BeforeAll
	void beforeAll( ){
//		Exercice exercice1 = new Exercice();
//		exercice1.setMuscleCible("Biceps");
//		exercice1.setNom("Curl marteau");
//		exerciceController.save(mapperApi.convertToDto(exercice1));
	}
	
	@Test
	void getAllExercices() {
		List<Exercice> exercices = new ArrayList<Exercice>();
		exercices.add(new Exercice());
		exercices.add(new Exercice());
		exercices.add(new Exercice());
		exercices.add(new Exercice());
		ResponseEntity<List<ExerciceAPI>> results = exerciceController.getAll();
		System.out.println(results.getBody().size());
		System.out.println(exercices.size());
		Assertions.assertEquals(exercices.size(), results.getBody().size());
		
	}
}
