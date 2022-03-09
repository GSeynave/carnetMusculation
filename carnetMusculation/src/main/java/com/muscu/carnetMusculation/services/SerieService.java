package com.muscu.carnetMusculation.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muscu.carnetMusculation.entities.Serie;
import com.muscu.carnetMusculation.repositories.SerieRepository;


@Service
public class SerieService {
	@Autowired
	private SerieRepository serieRepository;

	@Transactional
	public Serie save(Serie serie) {
		return serieRepository.save(serie);
	}

	@Transactional
	public Serie findById(Long id) {
		return serieRepository.findById(id).orElseThrow(() -> 
		new EntityNotFoundException("Serie not found with id :" +id));
	}

	@Transactional
	public List<Serie> getAll(){
		return (List<Serie>) serieRepository.findAll();
	}

	@Transactional
	public void delete(Serie serie){
		serieRepository.delete(serie);
	}

	public List<Serie> findByExerciceId(Long id) {
		return serieRepository.findByExerciceId(id);
	}

//	public List<SerieLineAPI> getSerieLineAPIByExerciceId(long id){
//		return convertSerieToSerieLineAPI(findByExerciceId(id));
//	}
	
//	private List<SerieLineAPI> convertSerieToSerieLineAPI(List<Serie> series) {
//		List<SerieLineAPI> serieLines = new ArrayList<SerieLineAPI>();
//		
//		//Creer autant de serieLineApi qu'il y a de 'entrainement différent dans series'
//		
//		
//		List<BilanSeance> entrainements = new ArrayList<BilanSeance>();
//
//		for(Serie serie : series) {
//			if(!entrainements.contains(serie.getEntrainement())) {
//				System.out.println("entrainement ajouté : " +serie.getEntrainement().getId());
//				entrainements.add(serie.getEntrainement());
//			}
//		}
//
//		List<String> poids = new ArrayList<String>();
//		List<String> rep = new ArrayList<String>();
//		List<String> recup = new ArrayList<String>();
//		
//		for(BilanSeance entrainement : entrainements) {
//			poids.clear();
//			rep.clear();
//			recup.clear();
//			SerieLineAPI serieLineApi = new SerieLineAPI();
//			for(Serie serie : series) {
//				if(serie.getEntrainement().getId() == entrainement.getId()) {
//					System.out.println("in if");
//					poids.add(serie.getPoids());
//					rep.add(serie.getRep());
//					recup.add(serie.getRecup());
//				}
//			}
//			System.out.println("poids : " +poids.size());
//			serieLineApi.setDate(entrainement.getDate().toString());
//			serieLineApi.setPoids(poids);
//			serieLineApi.setRecup(recup);
//			serieLineApi.setRep(rep);
//			serieLines.add(serieLineApi);
//		}
//		return serieLines;
//	}
}
