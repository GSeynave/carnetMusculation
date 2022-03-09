package com.muscu.carnetMusculation.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.repositories.ProgrammeRepository;


@Service
public class ProgrammeService {

	@Autowired
	private ProgrammeRepository programmeRepository;


	@Transactional
	public Programme save(Programme programme) {

		return programmeRepository.save(programme);
	}

	@Transactional
	public Programme findById(Long id) {
		return programmeRepository.findById(id).orElseThrow(() -> 
		new EntityNotFoundException("Exercice not found with id :" +id));
	}

	@Transactional
	public List<Programme> getAll(){
		return (List<Programme>) programmeRepository.findAll();
	}

	@Transactional
	public void delete(Programme programme){
		programmeRepository.delete(programme);
	}
}

