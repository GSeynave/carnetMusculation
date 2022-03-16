package com.muscu.carnetMusculation.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.muscu.carnetMusculation.entities.Entrainement;

public interface IEntrainementRepository extends PagingAndSortingRepository<Entrainement, Long> {

	List<Entrainement> findByProgrammeId(Long id);
}
