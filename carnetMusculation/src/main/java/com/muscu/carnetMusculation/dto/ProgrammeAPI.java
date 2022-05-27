package com.muscu.carnetMusculation.dto;

import java.time.LocalDate;
import java.util.List;

public class ProgrammeAPI {

	private Long id;
	private String nom;
	private LocalDate dateCreation;
	private LocalDate dateModification;
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
	public LocalDate getDateCreation() {
		return dateCreation;
	}
	public LocalDate getDateModification() {
		return dateModification;
	}
	public void setDateModification(LocalDate dateModification) {
		this.dateModification = dateModification;
	}
	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}
	public List<Long> getEntrainementsIds() {
		return entrainementsIds;
	}
	public void setEntrainementsIds(List<Long> entrainementsIds) {
		this.entrainementsIds = entrainementsIds;
	}
}
