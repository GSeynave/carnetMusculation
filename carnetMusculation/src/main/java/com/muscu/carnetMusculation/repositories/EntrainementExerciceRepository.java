package com.muscu.carnetMusculation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.muscu.carnetMusculation.entities.EntrainementExercice;

public interface EntrainementExerciceRepository extends PagingAndSortingRepository<EntrainementExercice, Long> {

	@Query("SELECT e"
		+ " FROM EntrainementExercice e"
		+ " WHERE e.entrainement.id = :entrainementId"
		+ " AND e.exercice.id = :exerciceId")
	EntrainementExercice findByEntrainementIdAndExerciceId(
			@Param("entrainementId") Long entrainementId,
			@Param("exerciceId") Long exerciceId);

	@Query("SELECT e"
		+ " FROM EntrainementExercice e"
		+ " WHERE e.entrainement.id = :entrainementId")
	List<EntrainementExercice> findAllByEntrainementId(@Param("entrainementId") Long entrainementId);

	@Modifying
	@Query("DELETE"
		+ " FROM EntrainementExercice e"
		+ " WHERE e.entrainement.id = :entrainementId"
		+ " AND e.exercice.id in :exerciceIdList")
	int deleteByEntrainementIdAndExerciceIdIn(@Param("entrainementId") long id, @Param("exerciceIdList") List<Long> exerciceIdList);

	@Modifying
	@Query("DELETE"
		+ " FROM EntrainementExercice e"
		+ " WHERE e.id in :ids")
	int deleteAllByIdIn(@Param("ids") List<Long> ids);

	EntrainementExercice save(EntrainementExercice entrainementExercice);
	
	@Query("SELECT new java.lang.Boolean(count(e) > 0)"
		+ " FROM EntrainementExercice e"
		+ " WHERE e.entrainement.id = :entrainementId")
	boolean existsByEntrainementId(@Param("entrainementId") long entrainementId);
    
	@Query("SELECT new java.lang.Boolean(count(e) > 0)"
		+ " FROM EntrainementExercice e"
		+ " WHERE e.entrainement.id = :entrainementId"
		+ " AND e.exercice.id = :exerciceId")
	boolean existsByExerciceIdAndEntrainementId(long exerciceId, long entrainementId);
}
