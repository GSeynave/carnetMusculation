package com.muscu.carnetMusculation.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.repositories.ISeanceRepository;
import com.muscu.carnetMusculation.utils.SeanceState;

@Repository
public class SeanceRepositoryImpl implements ISeanceRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Seance findByEntrainementIdAndState(Long entrainementId, SeanceState state) {
		TypedQuery<Seance> query = em.createNamedQuery("seance.findByEntrainementIdAndState", Seance.class);
		query.setParameter("entrainementId", entrainementId);
		query.setParameter("state", state);
		return query.getSingleResult();
	}

	@Override
	public Seance save(Seance seance) {
		if (seance != null) {
			em.persist(seance);
		}
		return seance;
	}

}
