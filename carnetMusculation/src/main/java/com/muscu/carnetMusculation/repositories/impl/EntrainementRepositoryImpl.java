package com.muscu.carnetMusculation.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.muscu.carnetMusculation.entities.Entrainement;
import com.muscu.carnetMusculation.repositories.IEntrainementRepository;

@Repository
public class EntrainementRepositoryImpl implements IEntrainementRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Entrainement findById(Long id) {
		TypedQuery<Entrainement> query = em.createNamedQuery("entrainement.findById", Entrainement.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public List<Entrainement> findByProgrammeId(Long programmeId) {
		TypedQuery<Entrainement> query = em.createNamedQuery("entrainement.findByProgrammeId", Entrainement.class);
		query.setParameter("programmeId", programmeId);
		return query.getResultList();
	}

	@Override
	public Entrainement findByNom(String nom) {
		TypedQuery<Entrainement> query = em.createNamedQuery("entrainement.findByNom", Entrainement.class);
		query.setParameter("nom", nom);
		return query.getSingleResult();
	}

	@Override
	public Entrainement save(Entrainement entrainement) {
		if(entrainement != null) {
			em.persist(entrainement);
		}
		return entrainement;
	}

	@Override
	public boolean existsById(long id) {
		return this.findById(id) != null ? true: false;
	}

	@Override
	public void deleteById(long id) {
		Query query = em.createNamedQuery("entrainement.deleteById");
		query.setParameter("id", id);
		query.executeUpdate();
		
	}
	
	
}
