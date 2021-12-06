package com.muscu.carnetMusculation.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.muscu.carnetMusculation.entities.Seance;

public interface SeanceRepository extends CrudRepository<Seance, Long> {

	List<Seance> findByProgrammeId(Long id);

}
