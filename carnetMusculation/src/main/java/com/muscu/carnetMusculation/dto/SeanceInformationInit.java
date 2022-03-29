package com.muscu.carnetMusculation.dto;

import java.util.List;

public class SeanceInformationInit {
	
	private Long programmeId;
	private Long  entrainementId;
	private String entrainementNom;
	private List<Details> detailsExercice;
	
	public Long getProgrammeId() {
		return programmeId;
	}
	public void setProgrammeId(Long programmeId) {
		this.programmeId = programmeId;
	}
	public Long getEntrainementId() {
		return entrainementId;
	}
	public void setEntrainementId(Long entrainementId) {
		this.entrainementId = entrainementId;
	}
	public String getEntrainementNom() {
		return entrainementNom;
	}
	public void setEntrainementNom(String entrainementNom) {
		this.entrainementNom = entrainementNom;
	}
	public List<Details> getDetailsExercice() {
		return detailsExercice;
	}
	public void setDetailsExercice(List<Details> detailsExercice) {
		this.detailsExercice = detailsExercice;
	}
	
	
}
