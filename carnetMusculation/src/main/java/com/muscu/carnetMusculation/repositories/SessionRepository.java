package com.muscu.carnetMusculation.repositories;

import org.springframework.data.repository.CrudRepository;

import com.muscu.carnetMusculation.entities.Session;

public interface SessionRepository extends CrudRepository<Session, Long> {

}