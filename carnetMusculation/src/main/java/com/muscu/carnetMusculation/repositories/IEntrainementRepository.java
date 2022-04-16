package com.muscu.carnetMusculation.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.muscu.carnetMusculation.entities.Entrainement;

public interface IEntrainementRepository {

	Entrainement findById(Long id);
	
	List<Entrainement> findByProgrammeId(Long programmeId);

	Entrainement findByNom(String nom);
	
	Entrainement save(Entrainement entrainement);
	
	boolean existsById(long id);
	
	void deleteById(long id);
}
