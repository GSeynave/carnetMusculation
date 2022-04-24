package com.muscu.carnetMusculation.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.utils.SeanceState;

public interface SeanceRepository extends PagingAndSortingRepository<Seance, Long>{
	
	@Query("SELECT s"
		+ " FROM Seance s"
		+ " WHERE s.entrainement.id = :entrainementId"
		+ " AND s.state = :state")
	Seance findByEntrainementIdAndState(
			@Param("entrainementId") Long entrainementId,
			@Param("state") SeanceState state);

	Seance save(Seance seance);

	@Query("SELECT new java.lang.Boolean(count(s) > 0)"
		+ " FROM Seance s"
		+ " WHERE s.entrainement.id = :entrainementId"
		+ " AND s.state = :state")
	boolean existsByEntrainementIdAndState(
			@Param("entrainementId") long entrainementId,
			@Param("state") SeanceState state);
}
