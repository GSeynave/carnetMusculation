package com.muscu.carnetMusculation.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.muscu.carnetMusculation.entities.Entrainement;

public interface IEntrainementRepository extends PagingAndSortingRepository<Entrainement, Long> {

	Optional<Entrainement> findById(Long id);
	
	List<Entrainement> findByProgrammeId(Long id);

	Entrainement findByNom(String nom);
}
