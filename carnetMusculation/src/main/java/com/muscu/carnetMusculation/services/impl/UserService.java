package com.muscu.carnetMusculation.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.User;
import com.muscu.carnetMusculation.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	

}
