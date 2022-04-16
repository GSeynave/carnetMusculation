package com.muscu.carnetMusculation.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
		return this.programmeRepository.save(programme);
	}

	@Override
	@Transactional
	public Programme findById(Long id) {
		return this.programmeRepository.findById(id);
	}

	@Override
	@Transactional
	public List<Programme> findPaginated(int pageNo, int size, String sort){
		Pageable page = PageRequest.of(pageNo, size, Sort.by(sort));
		List<Programme> pageResult = programmeRepository.findPaginated(page);
		return pageResult;
	}

	@Override
	@Transactional
	public void deleteById(Long id){
		programmeRepository.deleteById(id);
	}

//	@Override
//	@Transactional
//	public boolean existsById(Long id) {
//		return programmeRepository.existsById(id);
//	}

	@Override
	@Transactional
	public long countAll() {
		return programmeRepository.countAll();
	}
}

