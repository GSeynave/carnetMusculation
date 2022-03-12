package com.muscu.carnetMusculation.services.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.repositories.IProgrammeRepository;
import com.muscu.carnetMusculation.services.IProgrammeService;


@Service
public class ProgrammeServiceImpl implements IProgrammeService {

	@Autowired
	private IProgrammeRepository programmeRepository;


	@Transactional
	public Programme save(Programme programme) {
		return programmeRepository.save(programme);
	}

	@Transactional
	public Programme findById(Long id) {
		return programmeRepository.findById(id).orElseThrow(() -> 
		new EntityNotFoundException("Programme not found with id :" +id));
	}

	@Transactional
	public List<Programme> findAll(){
		return (List<Programme>) programmeRepository.findAll();
	}

	@Transactional
	public void deleteById(Long id){
		programmeRepository.deleteById(id);
	}

	@Override
	@Transactional
	public boolean existsById(Long id) {
		return programmeRepository.existsById(id);
	}
}

