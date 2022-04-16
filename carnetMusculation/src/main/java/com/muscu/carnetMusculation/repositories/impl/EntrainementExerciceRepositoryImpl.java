package com.muscu.carnetMusculation.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.muscu.carnetMusculation.entities.EntrainementExercice;
import com.muscu.carnetMusculation.repositories.IEntrainementExerciceRepository;

@Repository
public class EntrainementExerciceRepositoryImpl implements IEntrainementExerciceRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public EntrainementExercice findByEntrainementIdAndExerciceId(Long exerciceId, Long entrainementId) {
		TypedQuery<EntrainementExercice> query = em.createNamedQuery("entrainementExercice.findByEntrainementIdAndExerciceId", EntrainementExercice.class);
		query.setParameter("entrainementId", entrainementId);
		query.setParameter("exerciceId", exerciceId);
		return query.getSingleResult();
	}

	@Override
	public List<EntrainementExercice> findAllByEntrainementId(Long entrainementId) {
		TypedQuery<EntrainementExercice> query = em.createNamedQuery("entrainementExercice.findByEntrainementId", EntrainementExercice.class);
		query.setParameter("entrainementId", entrainementId);
		return query.getResultList();
	}

	@Override
	public void deleteByEntrainementIdAndExerciceIdIn(long entrainementId, List<Long> exerciceIds) {
		Query query = em.createNamedQuery("entrainementExercice.deleteByEntrainementIdAndExerciceIdIn");
		query.setParameter("entrainementId", entrainementId);
		query.setParameter("exerciceIds", exerciceIds);
		query.executeUpdate();
	}

	@Override
	public void deleteAllById(List<Long> ids) {
		Query query = em.createNamedQuery("entrainementExercice.deleteAllById");
		query.setParameter("ids", ids);
		query.executeUpdate();
		
	}

	@Override
	public EntrainementExercice save(EntrainementExercice entrainementExercice) {
		// TODO Auto-generated method stub
		return null;
	}

}
