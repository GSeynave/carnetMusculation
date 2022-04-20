package com.muscu.carnetMusculation.repositories;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.transaction.annotation.Transactional;

import com.muscu.carnetMusculation.CarnetMusculationApplication;
import com.muscu.carnetMusculation.SQLConfig;
import com.muscu.carnetMusculation.entities.Entrainement;
import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.services.ProgrammeService;
import com.muscu.carnetMusculation.utils.EntrainementType;

@TestInstance(Lifecycle.PER_CLASS)
@Transactional
@SpringBootTest(classes = { CarnetMusculationApplication.class, SQLConfig.class })
public class EntrainementRepositoryTest {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private EntrainementRepository entrainementRepository;
	@Autowired
	private ProgrammeService programmeService;
	
	private Date date = new Date();
	private Programme programme;
	
	@BeforeAll
	public void beforeAll() {

		programme = this.programmeService.findById(1l);
	}
	
	@Test
	public void findByIdTest() {
		Entrainement entrainement = this.entrainementBuilder(date, date, "Entrainement 1", EntrainementType.FULL_BODY, programme);
		Entrainement result = this.entrainementRepository.findById(entrainement.getId());
		Assertions.assertEquals(null, result);
		
		entrainement =this.entrainementRepository.save(entrainement);
		result = this.entrainementRepository.findById(entrainement.getId());
		Assertions.assertEquals(entrainement, result);
	}
	
	@Test
	public void findByNomTest() {
		Entrainement result = this.entrainementRepository.findByNom("Entrainement 1");
		Assertions.assertEquals(null, result);
		
		Entrainement entrainement = this.entrainementBuilder(date, date, "Entrainement 1", EntrainementType.FULL_BODY, programme);
		entrainement =this.entrainementRepository.save(entrainement);
		result = this.entrainementRepository.findByNom("Entrainement 1");
		
		Assertions.assertEquals(entrainement, result);
	}

	@Test
	public void existsByNomTest() {
		Boolean result = this.entrainementRepository.existsByNom("Entrainement 1");
		Assertions.assertEquals(false, result);

		Entrainement entrainement = this.entrainementBuilder(date, date, "Entrainement 1", EntrainementType.FULL_BODY, programme);
		entrainement =this.entrainementRepository.save(entrainement);
		result = this.entrainementRepository.existsByNom("Entrainement 1");
		Assertions.assertEquals(true, result);
		
	}
	
	public Entrainement entrainementBuilder(Date dateCreation, Date dateModification, String nom, EntrainementType entrainementType, Programme programme) {
		Entrainement entrainement = new Entrainement();
		entrainement.setDateCreation(dateCreation);
		entrainement.setDateModification(dateModification);
		entrainement.setNom(nom);
		entrainement.setProgramme(programme);
		return entrainement;
	}
	
	public Programme programmeBuilder(Date dateCreation, Date dateModification, String nom) {
		Programme programme = new Programme ();
		programme.setDateCreation(dateCreation);
		programme.setDateModification(dateModification);
		programme.setNom(nom);
		return programme;
	}
}
