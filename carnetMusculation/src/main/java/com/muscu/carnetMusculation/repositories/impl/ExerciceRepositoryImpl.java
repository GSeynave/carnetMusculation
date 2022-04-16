package com.muscu.carnetMusculation.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.repositories.IExerciceRepository;

@Repository
public class ExerciceRepositoryImpl implements IExerciceRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Exercice> findAll() {
		TypedQuery<Exercice> query = em.createNamedQuery("exercice.findAll", Exercice.class);
		return query.getResultList();
	}

	@Override
	public Exercice findById(long id) {
		TypedQuery<Exercice> query = em.createNamedQuery("exercice.findById", Exercice.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public void deleteById(long id) {
		Query query = em.createNamedQuery("exercice.deleteById");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	

}
