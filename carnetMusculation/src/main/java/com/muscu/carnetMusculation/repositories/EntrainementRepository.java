package com.muscu.carnetMusculation.repositories;

import org.springframework.data.repository.CrudRepository;

import com.muscu.carnetMusculation.entities.Entrainement;

public interface EntrainementRepository extends CrudRepository<Entrainement, Long>{
	
}