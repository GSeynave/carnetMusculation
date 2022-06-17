package com.muscu.carnetMusculation.dto;

import java.time.LocalDate;
import java.util.List;

import com.muscu.carnetMusculation.utils.EntrainementType;

public class EntrainementCreerAPI {
	
	private Long entrainementId;
	private LocalDate creationDate;
	private LocalDate modificationDate;
	private String nom;
	private EntrainementType type;
	private Long programmeId;
	private List<Details> details;
	
	public Long getEntrainementId() {
		return entrainementId;
	}

	public void setEntrainementId(Long entrainementId) {
		this.entrainementId = entrainementId;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(LocalDate modificationDate) {
		this.modificationDate = modificationDate;
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

	@Override
	public String toString() {
		return "EntrainementCreerAPI [entrainementId=" + entrainementId + ", creationDate=" + creationDate
				+ ", modificationDate=" + modificationDate + ", nom=" + nom + ", type=" + type + ", programmeId="
				+ programmeId + ", details=" + details + "]";
	}
	
	
}
