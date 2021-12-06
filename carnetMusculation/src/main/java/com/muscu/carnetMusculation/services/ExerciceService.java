package com.muscu.carnetMusculation.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.repositories.ExerciceRepository;

@Service
public class ExerciceService {
	@Autowired
	private ExerciceRepository exerciceRepository;


	@Transactional
	public Exercice save(Exercice exercice) {
		return exerciceRepository.save(exercice);
	}


	@Transactional
	public Exercice findById(Long id) {
		return exerciceRepository.findById(id).orElseThrow(() -> 
		new EntityNotFoundException("Exercice not found with id :" +id));
	}
	
	@Transactional
	public List<Exercice> findBySeances(Long id) {
		return exerciceRepository.findBySeancesId(id);
	}

	@Transactional
	public List<Exercice> getAll(){
		return (List<Exercice>) exerciceRepository.findAll();
	}

	@Transactional
	public void delete(Exercice exercice){
		exerciceRepository.delete(exercice);
	}
}
