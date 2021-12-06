package com.muscu.carnetMusculation.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.Entrainement;
import com.muscu.carnetMusculation.repositories.EntrainementRepository;


@Service
public class EntrainementService {
	@Autowired
	private EntrainementRepository entrainementRepository;

	@Transactional
	public Entrainement save(Entrainement entrainement) {
		return entrainementRepository.save(entrainement);
	}

	@Transactional
	public Entrainement findById(Long id) {
		return entrainementRepository.findById(id).orElseThrow(() -> 
		new EntityNotFoundException("Entrainement not found with id :" +id));
	}
	
	@Transactional
	public List<Entrainement> getAll(){
		return (List<Entrainement>) entrainementRepository.findAll();
	}

	@Transactional
	public void delete(Entrainement entrainement){
		entrainementRepository.delete(entrainement);
	}
}
