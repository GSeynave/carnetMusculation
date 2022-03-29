package com.muscu.carnetMusculation.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.muscu.carnetMusculation.entities.DetailsExercice;

public interface IDetailsExerciceRepository extends PagingAndSortingRepository<DetailsExercice, Long> {

	DetailsExercice findByEntrainementIdAndExerciceId(Long exerciceId, Long entrainementId);
	
	List<DetailsExercice> findAllByEntrainementId(Long entrainementId);
}
