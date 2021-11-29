package com.muscu.carnetMusculation.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.Program;
import com.muscu.carnetMusculation.repositories.ProgramRepository;

@Service
public class ProgramService {

	@Autowired
	private ProgramRepository programRepository;


	@Transactional
	public Program save(Program program) {

		return programRepository.save(program);
	}


	@Transactional
	public Program findById(Long id) {
		return programRepository.findById(id).orElseThrow(() -> 
		new EntityNotFoundException("Exercice not found with id :" +id));
	}

	@Transactional
	public List<Program> getAll(){
		return (List<Program>) programRepository.findAll();
	}

	@Transactional
	public void delete(Program program){
		programRepository.delete(program);
	}
}

