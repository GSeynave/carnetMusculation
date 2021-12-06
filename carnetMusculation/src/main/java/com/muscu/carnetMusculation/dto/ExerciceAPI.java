package com.muscu.carnetMusculation.dto;

import java.util.List;

public class ExerciceAPI {
	private Long id;
	private String nom;
	private String dateCreation;
	private String muscleCible;
	private List<Long> seanceIds;
	private List<Long> seriesId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getMuscleCible() {
		return muscleCible;
	}
	public void setMuscleCible(String muscleCible) {
		this.muscleCible = muscleCible;
	}
	public List<Long> getSeanceIds() {
		return seanceIds;
	}
	public void setSeanceIds(List<Long> seanceIds) {
		this.seanceIds = seanceIds;
	}
	public List<Long> getSeriesId() {
		return seriesId;
	}
	public void setSeriesId(List<Long> seriesId) {
		this.seriesId = seriesId;
	}

}
