package com.muscu.carnetMusculation.repositories;

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
import org.springframework.transaction.annotation.Transactional;

import com.muscu.carnetMusculation.CarnetMusculationApplication;
import com.muscu.carnetMusculation.SQLConfig;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.utils.EntityBuilder;

@TestInstance(Lifecycle.PER_CLASS)
@Transactional
@SpringBootTest(classes = { CarnetMusculationApplication.class, SQLConfig.class })
public class ExerciceRepositoryTest {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ExerciceRepository exerciceRepository;
	
	@Test
	public void testFindAll() {
		List<Exercice> result = this.exerciceRepository.findAll();
		Assertions.assertEquals(6, result.size());
	}
	
	@Test
	public void testFindById() {
		Optional<Exercice> result = this.exerciceRepository.findById(1l);
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void testDeleteById() {
		Exercice exercice = EntityBuilder.exerciceBuilder("test", "test");
		entityManager.persist(exercice);
		TypedQuery<Exercice> query = entityManager.createQuery("SELECT e FROM Exercice e WHERE e.nom = :nom", Exercice.class);
		query.setParameter("nom", "test");
		Exercice result = query.getSingleResult();
		
		long deletedRows = this.exerciceRepository.deleteById(result.getId().longValue());
		Assertions.assertEquals(1, deletedRows);
	}
}
