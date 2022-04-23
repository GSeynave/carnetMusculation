package com.muscu.carnetMusculation.services.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.repositories.ProgrammeRepository;


@Service
public class ProgrammeService {

	private final ProgrammeRepository programmeRepository;

	public ProgrammeService(ProgrammeRepository programmeRepository) {
		super();
		this.programmeRepository = programmeRepository;
	}

	@Transactional
	public Programme save(Programme programme) {
		return this.programmeRepository.save(programme);
	}

	@Transactional
	public Programme findById(Long id) {
		Optional<Programme> programme = this.programmeRepository.findById(id);
		if (programme.isPresent()) {
			return programme.get();
		} else {
			throw new EntityNotFoundException("Exercice non trouvé pour l'id : " +id);
		}
	}

	@Transactional
	public List<Programme> findPaginated(int pageNo, int size, String sort){
		Pageable page = PageRequest.of(pageNo, size, Sort.by(sort));
		Page<Programme> pageResult = programmeRepository.findAll(page);
		return pageResult.toList();
	}

	@Transactional
	public List<Programme> findAll(){
		return programmeRepository.findAll();
	}

	@Transactional
	public void deleteById(Long id){
		programmeRepository.deleteById(id);
	}

//	@Override
//	@Transactional
//	public boolean existsById(Long id) {
//		return programmeRepository.existsById(id);
//	}

	@Transactional
	public long countAll() {
		return programmeRepository.count();
	}
}

