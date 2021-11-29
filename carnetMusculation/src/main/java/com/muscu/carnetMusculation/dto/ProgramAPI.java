package com.muscu.carnetMusculation.dto;

import java.util.Set;

public class ProgramAPI {

	private Long id;
	private String name;
	private String creationDate;
	private Set<ExerciceAPI> exercices;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public Set<ExerciceAPI> getExercices() {
		return exercices;
	}
	public void setExercices(Set<ExerciceAPI> exercicesApi) {
		this.exercices = exercicesApi;
	}

}
