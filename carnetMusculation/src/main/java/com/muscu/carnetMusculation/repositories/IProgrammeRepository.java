package com.muscu.carnetMusculation.repositories;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.muscu.carnetMusculation.entities.Programme;

@Repository
public interface IProgrammeRepository {

	List<Programme> findPaginated(Pageable pageable);
	Programme findById(long id);
	long countAll();
	Programme save(Programme programme);
	void deleteById(long id);
}
