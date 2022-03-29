package com.muscu.carnetMusculation.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.muscu.carnetMusculation.entities.Exercice;

public interface IExerciceRepository extends PagingAndSortingRepository<Exercice, Long>{
	
	List<Exercice> findAll();
}
