package com.muscu.carnetMusculation.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.repositories.IProgrammeRepository;
import com.muscu.carnetMusculation.services.IProgrammeService;


@Service
public class ProgrammeServiceImpl implements IProgrammeService {

	@Autowired
	private IProgrammeRepository programmeRepository;

	@Override
	@Transactional
	public Programme save(Programme programme) {
		return programmeRepository.save(programme);
	}

	@Override
	@Transactional
	public Programme findById(Long id) {
		return programmeRepository.findById(id).orElseThrow(() -> 
		new EntityNotFoundException("Programme not found with id :" +id));
	}

	@Override
	@Transactional
	public List<Programme> findAll(){
		return (List<Programme>) programmeRepository.findAll();
	}

	@Override
	@Transactional
	public List<Programme> findPaginated(int pageNo, int size, String sort){
		Pageable page = PageRequest.of(pageNo, size, Sort.by(sort));
		Page<Programme> pageResult = programmeRepository.findAll(page);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}
		return new ArrayList<Programme>();
	}

	@Override
	@Transactional
	public void deleteById(Long id){
		programmeRepository.deleteById(id);
	}

	@Override
	@Transactional
	public boolean existsById(Long id) {
		return programmeRepository.existsById(id);
	}

	@Override
	@Transactional
	public int countAll() {
		return programmeRepository.countAll();
	}
}

