package com.muscu.carnetMusculation.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.muscu.carnetMusculation.entities.Serie;
import com.muscu.carnetMusculation.repositories.ISerieRepository;

@Repository
public class SerieRepositoryImpl implements ISerieRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Serie> findByExerciceId(Long id) {
		TypedQuery<Serie> query = em.createNamedQuery("serie.findById", Serie.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<Serie> findBySeanceIdAndNumeroSerie(Long seanceId, String numeroSerie) {
		TypedQuery<Serie> query = em.createNamedQuery("serie.findBySeanceIdAndNumeroSerie", Serie.class);
		query.setParameter("seanceId", seanceId);
		query.setParameter("numeroSerie", numeroSerie);
		return query.getResultList();
	}

	@Override
	public List<Serie> findBySeanceId(Long seanceId) {
		TypedQuery<Serie> query = em.createNamedQuery("serie.findBySeanceId", Serie.class);
		query.setParameter("seanceId", seanceId);
		return query.getResultList();
	}

	@Override
	public Serie findBySeanceIdAndNumeroSerieAndEntrainementIdAndExerciceId(Long seanceId, String numeroSerie,
			Long entrainementId, Long exerciceId) {
		TypedQuery<Serie> query = em.createNamedQuery("serie.findBySeanceIdAndNumeroSerieAndEntrainementIdAndExerciceId", Serie.class);
		query.setParameter("seanceId", seanceId);
		query.setParameter("numeroSerie", numeroSerie);
		query.setParameter("entrainementId", entrainementId);
		query.setParameter("exerciceId", exerciceId);
		return query.getSingleResult();
	}

	@Override
	public void deleteByEntrainementIdAndSeanceIdAndExerciceIdIn(long entrainementId, Long seanceId,
			List<Long> exerciceIdList) {
		Query query = em.createNamedQuery("serie.deleteByEntrainementIdAndSeanceIdAndExerciceIdIn");
		em.remove(query);
	}

	@Override
	public Serie save(Serie serie) {
		if(serie != null) {
			em.persist(serie);
		}
		return serie;
		
	}

	@Override
	public void deleteByIdIn(List<Long> ids) {
		Query query = em.createNamedQuery("serie.deleteByIdIn");
		query.setParameter("ids", ids);
		em.remove(query);
	}

}
