package com.muscu.carnetMusculation.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.muscu.carnetMusculation.entities.Programme;

@Repository
public class ProgrammeRepository implements IProgrammeRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Programme> findAll() {
		TypedQuery<Programme> query = em.createNamedQuery("programme.findAll", Programme.class);
		return query.getResultList();
	}

	@Override
	public List<Programme> findPaginated(Pageable pageable) {
		TypedQuery<Programme> query = em.createNamedQuery("programme.findPaginated", Programme.class);
		query.setMaxResults(pageable.getPageSize());
		query.setFirstResult((int)pageable.getOffset());
		query.setParameter("sort", pageable.getSort());
		return query.getResultList();
	}
	
	@Override
	public Programme findById(long id) {
		TypedQuery<Programme> query = em.createNamedQuery("programme.findById", Programme.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	@Override
	public long countAll() {
		Query query = em.createNamedQuery("programme.countAll");
		return ((Number) query.getSingleResult()).longValue();
	}
	
	@Override
	public void save(Programme programme) {
		if(programme != null) {
			em.persist(programme);
		}
	}

	@Override
	public void deleteById(long id) {
		Query query = em.createNamedQuery("programme.deleteById");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
}
