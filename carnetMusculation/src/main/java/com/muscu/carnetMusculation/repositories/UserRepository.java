package com.muscu.carnetMusculation.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.muscu.carnetMusculation.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

	@Query("SELECT u"
		+ " FROM User u"
		+ " WHERE u.username = :username"
		+ " or u.mail = :username")
	User findByUsernameOrEmail(String username);
	
}