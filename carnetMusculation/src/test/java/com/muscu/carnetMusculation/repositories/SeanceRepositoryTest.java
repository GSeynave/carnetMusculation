package com.muscu.carnetMusculation.repositories;

import java.time.LocalDate;

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
import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.utils.EntityBuilder;
import com.muscu.carnetMusculation.utils.EntrainementType;
import com.muscu.carnetMusculation.utils.SeanceState;

@TestInstance(Lifecycle.PER_CLASS)
@Transactional
@SpringBootTest(classes = { CarnetMusculationApplication.class, SQLConfig.class })
public class SeanceRepositoryTest {
	
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private SeanceRepository seanceRepository;
	@Test
	public void testFindByEntrainementIdAndState() {
		Seance result = this.seanceRepository.findByEntrainementIdAndState(1l, SeanceState.INIT);
		Assertions.assertEquals(1l, result.getId());
	}
	
	@Test
	public void testSave() {
		Programme programme = EntityBuilder.programmeBuilder(LocalDate.now(), LocalDate.now(), "test");
		entityManager.persist(programme);
		Entrainement entrainement = EntityBuilder.entrainementBuilder(LocalDate.now(), LocalDate.now(), "test", EntrainementType.FULL_BODY);
		entrainement.setProgramme(programme);
		entityManager.persist(entrainement);
		Seance seance = EntityBuilder.seanceBuilder(LocalDate.now(), SeanceState.INIT);
		seance.setEntrainement(entrainement);
		entityManager.persist(seance);
		
		Seance result = this.seanceRepository.findByEntrainementIdAndState(entrainement.getId(), SeanceState.INIT);
		Assertions.assertEquals(seance, result);
	}
	
	@Test
	public void testExistsByEntrainementIdAndState() {
		Boolean result = this.seanceRepository.existsByEntrainementIdAndState(1l, SeanceState.INIT);
		Assertions.assertTrue(result);
	}

}
