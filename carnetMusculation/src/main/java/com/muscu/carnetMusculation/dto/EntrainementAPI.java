package com.muscu.carnetMusculation.dto;

import java.util.List;

public class EntrainementAPI {
	
	private Long id;
	private String dateCreation;
	private String muscleCible;
	private Long programmeId;
	private List<Long> exerciceIds;
	
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
	public String getMuscleCible() {
		return muscleCible;
	}
	public void setMuscleCible(String muscleCible) {
		this.muscleCible = muscleCible;
	}
	public Long getProgrammeId() {
		return programmeId;
	}
	public void setProgrammeId(Long programmeId) {
		this.programmeId = programmeId;
	}
	public List<Long> getExerciceIds() {
		return exerciceIds;
	}
	public void setExerciceIds(List<Long> exerciceIds) {
		this.exerciceIds = exerciceIds;
	}

}
