package com.muscu.carnetMusculation.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.muscu.carnetMusculation.entities.EntrainementExercice;

public interface IEntrainementExerciceRepository {

	EntrainementExercice findByEntrainementIdAndExerciceId(Long entrainementId, Long exerciceId);
	
	List<EntrainementExercice> findAllByEntrainementId(Long entrainementId);

	void deleteByEntrainementIdAndExerciceIdIn(long id, List<Long> exerciceIdList);
	
	void deleteAllById(List<Long> ids);
	
	EntrainementExercice save(EntrainementExercice entrainementExercice);
}
