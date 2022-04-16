package com.muscu.carnetMusculation.dto;

import java.util.List;

public class ProgrammeAPI {

	private Long id;
	private String nom;
	private String dateCreation;
	private String dateModification;
	private List<Long> entrainementsIds;
	
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
	public String getDateModification() {
		return dateModification;
	}
	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public List<Long> getEntrainementsIds() {
		return entrainementsIds;
	}
	public void setEntrainementsIds(List<Long> entrainementsIds) {
		this.entrainementsIds = entrainementsIds;
	}
}
