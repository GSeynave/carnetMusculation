package com.muscu.carnetMusculation.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.repositories.SeanceRepository;


@Service
public class SeanceService {
	@Autowired
	private SeanceRepository seanceRepository;

	@Transactional
	public Seance save(Seance session) {
		System.out.println("saving session with exercice : " +session.getExercices());
		return seanceRepository.save(session);
	}

	@Transactional
	public Seance findById(Long id) {
		return seanceRepository.findById(id).orElseThrow(() -> 
		new EntityNotFoundException("Exercice not found with id :" +id));
	}
	
	@Transactional
	public List<Seance> getAll(){
		return (List<Seance>) seanceRepository.findAll();
	}

	@Transactional
	public void delete(Seance session){
		seanceRepository.delete(session);
	}

	public List<Seance> findByProgrammeId(Long id) {
		return seanceRepository.findByProgrammeId(id);
	}
}
