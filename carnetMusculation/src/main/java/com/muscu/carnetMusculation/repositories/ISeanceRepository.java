package com.muscu.carnetMusculation.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.utils.SeanceState;

public interface ISeanceRepository extends PagingAndSortingRepository<Seance, Long> {
	
	Seance findByEntrainementIdAndState(Long entrainementId, SeanceState state);

}
