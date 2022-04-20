package com.muscu.carnetMusculation.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.muscu.carnetMusculation.entities.Exercice;

public interface ExerciceRepository extends PagingAndSortingRepository<Exercice, Long>{
	
	List<Exercice> findAll();
	Exercice findById(long id);
	void deleteById(long id);
}
