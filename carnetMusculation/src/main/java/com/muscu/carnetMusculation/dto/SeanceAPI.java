package com.muscu.carnetMusculation.dto;

import java.util.List;

public class SeanceAPI {
	private Long id;
	private String nom;
	private String dateCreation;
	private String muscleCible;
	private Long programId;
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
	public String getMuscleCible() {
		return muscleCible;
	}
	public void setMuscleCible(String muscleCible) {
		this.muscleCible = muscleCible;
	}
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public List<Long> getExerciceIds() {
		return exerciceIds;
	}
	public void setExerciceIds(List<Long> exerciceIds) {
		this.exerciceIds = exerciceIds;
	}
	
}
