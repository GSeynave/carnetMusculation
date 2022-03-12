package com.muscu.carnetMusculation.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.muscu.carnetMusculation.entities.Programme;

@Repository
public interface IProgrammeRepository extends CrudRepository<Programme, Long> {

}
