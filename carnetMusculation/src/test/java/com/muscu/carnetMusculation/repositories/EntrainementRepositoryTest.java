package com.muscu.carnetMusculation.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.muscu.carnetMusculation.CarnetMusculationApplication;
import com.muscu.carnetMusculation.SQLConfig;
import com.muscu.carnetMusculation.entities.Entrainement;
import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.utils.EntrainementType;

@TestInstance(Lifecycle.PER_CLASS)
@Transactional
@SpringBootTest(classes = { CarnetMusculationApplication.class, SQLConfig.class })
public class EntrainementRepositoryTest {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private EntrainementRepository entrainementRepository;
	
	@Test
	public void testFindById() {
		Optional<Entrainement> result = this.entrainementRepository.findById(1l);
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void testFindByProgrammeId() {
		List<Entrainement> result = this.entrainementRepository.findByProgrammeId(2l);
		Assertions.assertEquals(4, result.size());
	}
	
	@Test
	public void testFindByNom() {
		Entrainement result = this.entrainementRepository.findByNom("Entrainement 1");
		Assertions.assertNotNull(result);
		Assertions.assertEquals("Entrainement 1", result.getNom());
	}

	@Test
	public void testExistsByNom() {
		Boolean result = this.entrainementRepository.existsByNom("Entrainement 1");
		Assertions.assertEquals(true, result);
	}
	
	@Test
	public void testSave() {
		Programme programme = this.programmeBuilder(LocalDate.now(), LocalDate.now(), "test");
		this.entityManager.persist(programme);
		
		String nom = "EntrainementSaved";
		Entrainement entrainement = this.entrainementBuilder(LocalDate.now(), LocalDate.now(), nom, EntrainementType.FULL_BODY);
		entrainement.setProgramme(programme);
		this.entityManager.persist(entrainement);
		Entrainement result = this.entrainementRepository.findByNom(nom);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(nom, result.getNom());
	}
	
	@Test
	public void testExistsById() {
		Boolean result = this.entrainementRepository.existsById(1l);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	public void deleteById() {
		Programme programme = this.programmeBuilder(LocalDate.now(), LocalDate.now(), "test");
		this.entityManager.persist(programme);
		
		String nom = "EntrainementSaved";
		Entrainement entrainement = this.entrainementBuilder(LocalDate.now(), LocalDate.now(), nom, EntrainementType.FULL_BODY);
		entrainement.setProgramme(programme);
		this.entityManager.persist(entrainement);
		
		Entrainement result = this.entrainementRepository.findByNom(nom);
		Assertions.assertNotNull(result);
		
		this.entrainementRepository.deleteById(entrainement.getId());
		Optional<Entrainement> resultAfterDelete = this.entrainementRepository.findById(entrainement.getId());
		Assertions.assertFalse(resultAfterDelete.isPresent());
	}
	
	public Entrainement entrainementBuilder(LocalDate dateCreation, LocalDate dateModification, String nom, EntrainementType entrainementType) {
		Entrainement entrainement = new Entrainement();
		entrainement.setDateCreation(dateCreation);
		entrainement.setDateModification(dateModification);
		entrainement.setNom(nom);
		return entrainement;
	}
	
	public Programme programmeBuilder(LocalDate dateCreation, LocalDate dateModification, String nom) {
		Programme programme = new Programme ();
		programme.setDateCreation(dateCreation);
		programme.setDateModification(dateModification);
		programme.setNom(nom);
		return programme;
	}
}
