package com.muscu.carnetMusculation.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.utils.SeanceState;

public interface SeanceRepository extends PagingAndSortingRepository<Seance, Long>{
	
	Seance findByEntrainementIdAndState(Long entrainementId, SeanceState state);

	Seance save(Seance seance);

	boolean existsByEntrainementIdAndState(long entrainementId, SeanceState state);
}
