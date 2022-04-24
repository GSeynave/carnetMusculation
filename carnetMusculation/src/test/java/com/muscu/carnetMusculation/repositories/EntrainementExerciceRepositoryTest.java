package com.muscu.carnetMusculation.repositories;

import java.time.LocalDate;
import java.util.List;

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
import com.muscu.carnetMusculation.entities.EntrainementExercice;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.utils.EntityBuilder;
import com.muscu.carnetMusculation.utils.EntrainementType;

@TestInstance(Lifecycle.PER_CLASS)
@Transactional
@SpringBootTest(classes = { CarnetMusculationApplication.class, SQLConfig.class })
public class EntrainementExerciceRepositoryTest {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private EntrainementExerciceRepository entrainementExerciceRepository;
	
	@Test
	public void testFindByEntrainementIdAndExerciceId() {
		EntrainementExercice result = this.entrainementExerciceRepository.findByEntrainementIdAndExerciceId(1l,  1l);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void testFindAllByEntrainementId() {
		List<EntrainementExercice> result = this.entrainementExerciceRepository.findAllByEntrainementId(1l);
		Assertions.assertEquals(3, result.size());
	}
	
	@Test
	public void testDeleteByEntrainementIdAndExerciceId() {
		Programme programme = EntityBuilder.programmeBuilder(LocalDate.now(), LocalDate.now(), "test");
		entityManager.persist(programme);
		Entrainement entrainement = EntityBuilder.entrainementBuilder(LocalDate.now(), LocalDate.now(), "test", EntrainementType.FULL_BODY);
		entrainement.setProgramme(programme);
		entityManager.persist(entrainement);
		
		Exercice exercice1 = EntityBuilder.exerciceBuilder("test1", "test1");
		Exercice exercice2 = EntityBuilder.exerciceBuilder("test2", "test2");
		entityManager.persist(exercice1);
		entityManager.persist(exercice2);
		EntrainementExercice entrainementExercice1 = new EntrainementExercice();
		entrainementExercice1.setEntrainement(entrainement);
		entrainementExercice1.setExercice(exercice1);
		entrainementExercice1.setNbSerie(4);
		EntrainementExercice entrainementExercice2 = new EntrainementExercice();
		entrainementExercice2.setEntrainement(entrainement);
		entrainementExercice2.setExercice(exercice2);
		entrainementExercice2.setNbSerie(4);
		entityManager.persist(entrainementExercice1);
		entityManager.persist(entrainementExercice2);
		
		long deletedRows = this.entrainementExerciceRepository.deleteByEntrainementIdAndExerciceIdIn(entrainement.getId(), List.of(exercice1.getId(), exercice2.getId()));
		Assertions.assertEquals(2, deletedRows);
	}
	
	@Test
	public void testDeleteByIdIn() {
		Programme programme = EntityBuilder.programmeBuilder(LocalDate.now(), LocalDate.now(), "test");
		entityManager.persist(programme);
		Entrainement entrainement = EntityBuilder.entrainementBuilder(LocalDate.now(), LocalDate.now(), "test", EntrainementType.FULL_BODY);
		entrainement.setProgramme(programme);
		entityManager.persist(entrainement);
		
		Exercice exercice1 = EntityBuilder.exerciceBuilder("test1", "test1");
		Exercice exercice2 = EntityBuilder.exerciceBuilder("test2", "test2");
		entityManager.persist(exercice1);
		entityManager.persist(exercice2);
		EntrainementExercice entrainementExercice1 = new EntrainementExercice();
		entrainementExercice1.setEntrainement(entrainement);
		entrainementExercice1.setExercice(exercice1);
		entrainementExercice1.setNbSerie(4);
		EntrainementExercice entrainementExercice2 = new EntrainementExercice();
		entrainementExercice2.setEntrainement(entrainement);
		entrainementExercice2.setExercice(exercice2);
		entrainementExercice2.setNbSerie(4);
		entityManager.persist(entrainementExercice1);
		entityManager.persist(entrainementExercice2);
		
		long deletedRows = this.entrainementExerciceRepository.deleteAllByIdIn(List.of(entrainementExercice1.getId(), entrainementExercice2.getId()));
		Assertions.assertEquals(2, deletedRows);
	}
	
	@Test
	public void testExistsByEntrainementId() {
		Boolean result = this.entrainementExerciceRepository.existsByEntrainementId(1l);
		Assertions.assertTrue(result);
	}
}
