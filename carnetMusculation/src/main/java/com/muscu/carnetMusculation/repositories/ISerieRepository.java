package com.muscu.carnetMusculation.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.muscu.carnetMusculation.entities.Serie;

public interface ISerieRepository extends CrudRepository<Serie, Long> {

	List<Serie> findByExerciceId(Long id);

}
