package com.muscu.carnetMusculation.dto;

import java.util.Date;
import java.util.List;

import com.muscu.carnetMusculation.utils.EntrainementType;

public class EntrainementCreerAPI {
	
	private Date creationDate;
	private String nom;
	private EntrainementType type;
	private Long programmeId;
	private List<Details> details;
	
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public EntrainementType getType() {
		return type;
	}
	public void setType(EntrainementType type) {
		this.type = type;
	}
	public Long getProgrammeId() {
		return programmeId;
	}
	public void setProgrammeId(Long programmeId) {
		this.programmeId = programmeId;
	}
	public List<Details> getDetails() {
		return details;
	}
	public void setDetails(List<Details> details) {
		this.details = details;
	}
	
}
