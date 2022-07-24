package com.muscu.carnetMusculation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.User;
import com.muscu.carnetMusculation.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public boolean findByUsernameOrEmail(User user) {
		if (user.getUsername().trim().isEmpty()) {
			throw new UsernameNotFoundException("Le user name est non renseigné");
		}
		
		User userFound = this.userRepository.findByUsernameOrEmail(user.getUsername());
		
		if(userFound == null) {
			throw new UsernameNotFoundException("Aucun compte pour le user name + " +user.getUsername());
		}
		
		if (user.getPassword().equals(userFound.getPassword())) {
			return true;			
		}
		return false;
	}

}
