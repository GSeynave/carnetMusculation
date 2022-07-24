package com.muscu.carnetMusculation.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.muscu.carnetMusculation.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
}