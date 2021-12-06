package com.muscu.carnetMusculation.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.repositories.ProgramRepository;

@Service
public class ProgramService {

	@Autowired
	private ProgramRepository programRepository;


	@Transactional
	public Programme save(Programme program) {

		return programRepository.save(program);
	}

	@Transactional
	public Programme findById(Long id) {
		return programRepository.findById(id).orElseThrow(() -> 
		new EntityNotFoundException("Exercice not found with id :" +id));
	}

	@Transactional
	public List<Programme> getAll(){
		return (List<Programme>) programRepository.findAll();
	}

	@Transactional
	public void delete(Programme program){
		programRepository.delete(program);
	}
}

