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
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.entities.Serie;
import com.muscu.carnetMusculation.utils.EntityBuilder;
import com.muscu.carnetMusculation.utils.EntrainementType;
import com.muscu.carnetMusculation.utils.SeanceState;

@TestInstance(Lifecycle.PER_CLASS)
@Transactional
@SpringBootTest(classes = { CarnetMusculationApplication.class, SQLConfig.class })
public class SerieRepositoryTest {
	
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private SerieRepository serieRepository;
	
	@Test
	public void testFindByExerciceId() {
		List<Serie> result = this.serieRepository.findByExerciceId(1l);
		Assertions.assertEquals(4, result.size());
	}
	
	@Test
	public void testFindBySeanceIdAndNumeroSerie() {
		List<Serie> result = this.serieRepository.findBySeanceIdAndNumeroSerie(1l, "0");
		Assertions.assertEquals(4, result.size());
	}
	
	@Test
	public void testFindBySeanceId() {
		List<Serie> result = this.serieRepository.findBySeanceId(1l);
		Assertions.assertEquals(7, result.size());
	}
	
	@Test
	public void testFindBySeanceIdAndNumeroSerieAndEntrainementIdAndExerciceId() {
		Serie result = this.serieRepository.findBySeanceIdAndNumeroSerieAndEntrainementIdAndExerciceId(1l, "0", 1l, 1l);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void testDeleteByEntrainementIdAndSeanceIdAndExerciceIdIn() {
		Programme programme = EntityBuilder.programmeBuilder(LocalDate.now(), LocalDate.now(), "test");
		entityManager.persist(programme);
		Entrainement entrainement = EntityBuilder.entrainementBuilder(LocalDate.now(), LocalDate.now(), "test", EntrainementType.FULL_BODY);
		entrainement.setProgramme(programme);
		entityManager.persist(entrainement);
		Seance seance = EntityBuilder.seanceBuilder(LocalDate.now(), SeanceState.INIT);
		seance.setEntrainement(entrainement);
		entityManager.persist(seance);
		Exercice exercice1 = EntityBuilder.exerciceBuilder("test", "test");
		Exercice exercice2 = EntityBuilder.exerciceBuilder("test", "test");
		entityManager.persist(exercice1);
		entityManager.persist(exercice2);
		Serie serie1 = EntityBuilder.serieBuilder("0", "26", "210", "10");
		serie1.setEntrainement(entrainement);
		serie1.setSeance(seance);
		serie1.setExercice(exercice1);
		entityManager.persist(serie1);
		Serie serie2 = EntityBuilder.serieBuilder("0", "26", "210", "10");
		serie2.setEntrainement(entrainement);
		serie2.setSeance(seance);
		serie2.setExercice(exercice1);
		entityManager.persist(serie2);
		
		long deletedRows = this.serieRepository.deleteByEntrainementIdAndSeanceIdAndExerciceIdIn(entrainement.getId(), seance.getId(), List.of(exercice1.getId(), exercice2.getId()));
		Assertions.assertEquals(2, deletedRows);
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
		Exercice exercice = EntityBuilder.exerciceBuilder("test", "test");
		entityManager.persist(exercice);
		Serie serie = EntityBuilder.serieBuilder("0", "26", "210", "10");
		serie.setEntrainement(entrainement);
		serie.setSeance(seance);
		serie.setExercice(exercice);
		
		this.serieRepository.save(serie);
		
		Optional<Serie> result = this.serieRepository.findById(serie.getId());
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void testDeleteByIdIn() {
		Programme programme = EntityBuilder.programmeBuilder(LocalDate.now(), LocalDate.now(), "test");
		entityManager.persist(programme);
		Entrainement entrainement = EntityBuilder.entrainementBuilder(LocalDate.now(), LocalDate.now(), "test", EntrainementType.FULL_BODY);
		entrainement.setProgramme(programme);
		entityManager.persist(entrainement);
		Seance seance = EntityBuilder.seanceBuilder(LocalDate.now(), SeanceState.INIT);
		seance.setEntrainement(entrainement);
		entityManager.persist(seance);
		Exercice exercice1 = EntityBuilder.exerciceBuilder("test", "test");
		Exercice exercice2 = EntityBuilder.exerciceBuilder("test", "test");
		entityManager.persist(exercice1);
		entityManager.persist(exercice2);
		Serie serie1 = EntityBuilder.serieBuilder("0", "26", "210", "10");
		serie1.setEntrainement(entrainement);
		serie1.setSeance(seance);
		serie1.setExercice(exercice1);
		entityManager.persist(serie1);
		Serie serie2 = EntityBuilder.serieBuilder("0", "26", "210", "10");
		serie2.setEntrainement(entrainement);
		serie2.setSeance(seance);
		serie2.setExercice(exercice1);
		entityManager.persist(serie2);
		
		long deletedRows = this.serieRepository.deleteByIdIn(List.of(serie1.getId(), serie2.getId()));
		Assertions.assertEquals(2, deletedRows);
	}
	
	@Test
	public void testExistsBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId() {
		Programme programme = EntityBuilder.programmeBuilder(LocalDate.now(), LocalDate.now(), "test");
		entityManager.persist(programme);
		Entrainement entrainement = EntityBuilder.entrainementBuilder(LocalDate.now(), LocalDate.now(), "test", EntrainementType.FULL_BODY);
		entrainement.setProgramme(programme);
		entityManager.persist(entrainement);
		Seance seance = EntityBuilder.seanceBuilder(LocalDate.now(), SeanceState.INIT);
		seance.setEntrainement(entrainement);
		entityManager.persist(seance);
		Exercice exercice = EntityBuilder.exerciceBuilder("test", "test");
		entityManager.persist(exercice);
		Serie serie = EntityBuilder.serieBuilder("0", "26", "210", "10");
		serie.setEntrainement(entrainement);
		serie.setSeance(seance);
		serie.setExercice(exercice);
		entityManager.persist(serie);
		
		Boolean result = this.serieRepository.existsBySeanceIdAndNumeroSerieAndExerciceIdAndEntrainementId(seance.getId(), "0", exercice.getId(), entrainement.getId());
		Assertions.assertTrue(result);
	}
	
	

}
