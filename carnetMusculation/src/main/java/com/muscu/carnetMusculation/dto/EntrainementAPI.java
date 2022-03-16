package com.muscu.carnetMusculation.dto;

import com.muscu.carnetMusculation.utils.EntrainementType;

public class EntrainementAPI {
	
	private Long id;
	private String dateCreation;
	private String nom;
	private Enum<EntrainementType> type;
	private Long seanceIds;
	private Long exerciceIds;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Enum<EntrainementType> getType() {
		return type;
	}
	public void setType(Enum<EntrainementType> type) {
		this.type = type;
	}
	public Long getSeanceIds() {
		return seanceIds;
	}
	public void setSeanceIds(Long seanceIds) {
		this.seanceIds = seanceIds;
	}
	public Long getExerciceIds() {
		return exerciceIds;
	}
	public void setExerciceIds(Long exerciceIds) {
		this.exerciceIds = exerciceIds;
	}

}
