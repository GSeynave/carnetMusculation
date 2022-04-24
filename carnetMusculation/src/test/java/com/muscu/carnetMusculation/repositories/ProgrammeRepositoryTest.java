package com.muscu.carnetMusculation.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.muscu.carnetMusculation.CarnetMusculationApplication;
import com.muscu.carnetMusculation.SQLConfig;
import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.utils.ObjectBuilder;

@TestInstance(Lifecycle.PER_CLASS)
@Transactional
@SpringBootTest(classes = { CarnetMusculationApplication.class, SQLConfig.class })
public class ProgrammeRepositoryTest {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private ProgrammeRepository programmeRepository;
	
	@Test
	public void testFindAllPaginated() {
		int pageNo = 0;
		int size = 5;
		String sort = "nom";
		Pageable pageable = PageRequest.of(pageNo, size, Sort.by(sort));
		Page<Programme> resultList = this.programmeRepository.findAll(pageable);
		Assertions.assertTrue(resultList.hasContent());
		Assertions.assertEquals(2, resultList.getTotalElements());
		
		Programme programme1 = ObjectBuilder.programmeBuilder(LocalDate.now(), LocalDate.now(), "A pgm1");
		Programme programme2 = ObjectBuilder.programmeBuilder(LocalDate.now(), LocalDate.now(), "A pgm2");
		Programme programme3 = ObjectBuilder.programmeBuilder(LocalDate.now(), LocalDate.now(), "A pgm3");
		Programme programme4 = ObjectBuilder.programmeBuilder(LocalDate.now(), LocalDate.now(), "A pgm4");
		Programme programme5 = ObjectBuilder.programmeBuilder(LocalDate.now(), LocalDate.now(), "A pgm5");
		
		entityManager.persist(programme1);
		entityManager.persist(programme2);
		entityManager.persist(programme3);
		entityManager.persist(programme4);
		entityManager.persist(programme5);

		resultList = this.programmeRepository.findAll(pageable);
		Assertions.assertTrue(resultList.hasContent());
		Assertions.assertEquals(7, resultList.getTotalElements());
		Assertions.assertEquals(2, resultList.getTotalPages());
		Assertions.assertEquals("A pgm1", resultList.get().findFirst().get().getNom());
	}
	
	@Test
	public void testFindAll() {
		List<Programme> result = this.programmeRepository.findAll();
		Assertions.assertEquals(result.size(), 2);
	}
	
	@Test
	public void testFindById() {
		Optional<Programme> result = this.programmeRepository.findById(1l);
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void testCount() {
		long result = this.programmeRepository.count();
		Assertions.assertEquals(2, result);
	}
	
	@Test
	public void testSave() {
		String nom = "test";
		Programme programme = ObjectBuilder.programmeBuilder(LocalDate.now(), LocalDate.now(), nom);
		entityManager.persist(programme);
		
		TypedQuery<Programme> query = entityManager.createQuery("SELECT p FROM Programme p WHERE p.nom = :nom", Programme.class);
		query.setParameter("nom", nom);
		Programme result = query.getSingleResult();
		Assertions.assertEquals(nom, result.getNom());
	}
	
	@Test
	public void testDeleteById() {
		String nom = "test";
		Programme programme = ObjectBuilder.programmeBuilder(LocalDate.now(), LocalDate.now(), nom);
		entityManager.persist(programme);
		
		TypedQuery<Programme> query = entityManager.createQuery("SELECT p FROM Programme p WHERE p.nom = :nom", Programme.class);
		query.setParameter("nom", nom);
		Programme result = query.getSingleResult();
		
		long deletedRows = this.programmeRepository.deleteById(result.getId());
		Assertions.assertEquals(1, deletedRows);;
	}
}
