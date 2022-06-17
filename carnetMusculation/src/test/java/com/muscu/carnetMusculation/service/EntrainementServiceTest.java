package com.muscu.carnetMusculation.service;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.muscu.carnetMusculation.CarnetMusculationApplication;
import com.muscu.carnetMusculation.SQLConfig;
import com.muscu.carnetMusculation.entities.Entrainement;
import com.muscu.carnetMusculation.entities.EntrainementExercice;
import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.services.impl.EntrainementService;
import com.muscu.carnetMusculation.services.impl.ExerciceService;
import com.muscu.carnetMusculation.services.impl.ProgrammeService;
import com.mysql.cj.Query;

@SpringBootTest(classes = { CarnetMusculationApplication.class, SQLConfig.class })
public class EntrainementServiceTest {

	@Autowired
	EntrainementService entrainementService; 
	
	@Autowired
	ExerciceService exerciceService;
	
	@Autowired
	ProgrammeService programmeService;
		
	@PersistenceContext
	EntityManager entityManager;
	
	@Test
	public void addExerciceInEntrainement() {
		LocalDate now = LocalDate.now();
		Programme programme = new Programme();
		programme.setDateCreation(now);
		programme.setDateModification(now);
		programme.setNom("Programme Test");
		programmeService.save(programme);
		
		Entrainement entrainement = new Entrainement();
		entrainement.setDateCreation(now);
		entrainement.setDateModification(now);
		entrainement.setNom("Entrainement Test");
		entrainement.setProgramme(programme);
		entrainementService.save(entrainement);
		
		
	}
	
	@Test
	public void removeExerciceInEntrainement() {
		
	}
	
	@Test
	public void updateExerciceInEntrainement() {
		
	}

}
