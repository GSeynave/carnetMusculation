package com.muscu.carnetMusculation.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.muscu.carnetMusculation.entities.EntrainementExercice;

public interface EntrainementExerciceRepository extends PagingAndSortingRepository<EntrainementExercice, Long> {

	EntrainementExercice findByEntrainementIdAndExerciceId(Long entrainementId, Long exerciceId);
	
	List<EntrainementExercice> findAllByEntrainementId(Long entrainementId);

	void deleteByEntrainementIdAndExerciceIdIn(long id, List<Long> exerciceIdList);
	
	void deleteAllByIdIn(List<Long> ids);
	
	EntrainementExercice save(EntrainementExercice entrainementExercice);

	boolean existsByEntrainementId(long entrainementId);
}
