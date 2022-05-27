package com.muscu.carnetMusculation.dto;

import java.time.LocalDate;
import java.util.List;

import com.muscu.carnetMusculation.utils.EntrainementType;

public class EntrainementAPI {
	
	private Long id;
	private LocalDate dateCreation;
	private Enum<EntrainementType> type;
	private String nom;
	private Long programmeId;
	private List<Long> seanceIds;
	private List<Long> serieIds;
	private List<Long> details;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Enum<EntrainementType> getType() {
		return type;
	}
	public void setType(Enum<EntrainementType> type) {
		this.type = type;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Long getProgrammeId() {
		return programmeId;
	}
	public void setProgrammeId(Long programmeId) {
		this.programmeId = programmeId;
	}
	public List<Long> getSeanceIds() {
		return seanceIds;
	}
	public void setSeanceIds(List<Long> seanceId) {
		this.seanceIds = seanceId;
	}
	public List<Long> getSerieIds() {
		return serieIds;
	}
	public void setSerieIds(List<Long> serieIds) {
		this.serieIds = serieIds;
	}
	public List<Long> getDetails() {
		return details;
	}
	public void setDetails(List<Long> details) {
		this.details = details;
	}
	

}
