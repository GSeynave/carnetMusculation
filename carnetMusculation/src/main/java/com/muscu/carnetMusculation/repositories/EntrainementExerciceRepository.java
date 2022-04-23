package com.muscu.carnetMusculation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.muscu.carnetMusculation.entities.EntrainementExercice;

public interface EntrainementExerciceRepository extends PagingAndSortingRepository<EntrainementExercice, Long> {

	@Query("SELECT e"
		+ " FROM EntrainementExercice e"
		+ " WHERE e.entrainement = :entrainementId"
		+ " AND e.exercice = :exerciceId")
	
	EntrainementExercice findByEntrainementIdAndExerciceId(
			@Param("entrainementId") Long entrainementId,
			@Param("exerciceId") Long exerciceId);

	@Query("SELECT e"
		+ " FROM EntrainementExercice e"
		+ " WHERE e.entrainement = :entrainementId")
	List<EntrainementExercice> findAllByEntrainementId(@Param("entrainementId") Long entrainementId);

	@Query("DELETE"
		+ " FROM EntrainementExercice e"
		+ " WHERE e.entrainement = :entrainementId"
		+ " AND e.exercice in :exerciceIdList")
	void deleteByEntrainementIdAndExerciceIdIn(@Param("entrainementId") long id, @Param("exerciceIdList") List<Long> exerciceIdList);

	@Query("DELETE"
		+ " FROM EntrainementExercice e"
		+ " WHERE e.id in :ids")
	void deleteAllByIdIn(@Param("ids") List<Long> ids);

	EntrainementExercice save(EntrainementExercice entrainementExercice);

	boolean existsByEntrainementId(long entrainementId);
}
