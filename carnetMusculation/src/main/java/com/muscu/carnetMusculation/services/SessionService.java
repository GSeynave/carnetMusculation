package com.muscu.carnetMusculation.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.Session;
import com.muscu.carnetMusculation.repositories.SessionRepository;

@Service
public class SessionService {
	@Autowired
	private SessionRepository sessionRepository;


	@Transactional
	public Session save(Session session) {
		return sessionRepository.save(session);
	}


	@Transactional
	public Session findById(Long id) {
		return sessionRepository.findById(id).orElseThrow(() -> 
		new EntityNotFoundException("Exercice not found with id :" +id));
	}

	@Transactional
	public List<Session> getAll(){
		return (List<Session>) sessionRepository.findAll();
	}

	@Transactional
	public void delete(Session session){
		sessionRepository.delete(session);
	}
}
