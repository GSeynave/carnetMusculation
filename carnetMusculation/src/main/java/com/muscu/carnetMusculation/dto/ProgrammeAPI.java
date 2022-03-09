package com.muscu.carnetMusculation.dto;

import java.util.List;

public class ProgrammeAPI {

	private Long id;
	private String nom;
	private String dateCreation;
	private List<Long> seanceIds;
	private List<Long> exerciceIds;
	
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
	public List<Long> getSeanceIds() {
		return seanceIds;
	}
	public void setSeanceIds(List<Long> seanceIds) {
		this.seanceIds = seanceIds;
	}
	public List<Long> getExerciceIds() {
		return exerciceIds;
	}
	public void setExerciceIds(List<Long> exerciceIds) {
		this.exerciceIds = exerciceIds;
	}
	
}
