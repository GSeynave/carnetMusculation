package com.muscu.carnetMusculation.controller;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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

@Transactional
@SpringBootTest(classes = { CarnetMusculationApplication.class, SQLConfig.class })
public class ProgrammeControllerTest {
	
	@PersistenceContext
	private EntityManager entityManager;
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

	@Test
	public void deleteByIdTest() {
		LocalDate now = LocalDate.now();
		String nom = "Test";
		Programme programme = programmeBuilder(nom, now, now);
		entityManager.persist(programme);
		TypedQuery<Programme> query = entityManager.createQuery("SELECT p FROM Programme p where p.nom = :nom", Programme.class);
		query.setParameter("nom", nom);
		List<Programme> result = query.getResultList();
		Assertions.assertEquals(1, result.size());

		programmeController.deleteById(programme.getId());
		result = query.getResultList();
		Assertions.assertEquals(0, result.size());
	}

	public Programme programmeBuilder(String nom, LocalDate creationDate, LocalDate modificationDate) {
		Programme programme = new Programme();
		programme.setNom(nom);
		programme.setDateCreation(creationDate);
		programme.setDateModification(modificationDate);
		return programme;
	}
}