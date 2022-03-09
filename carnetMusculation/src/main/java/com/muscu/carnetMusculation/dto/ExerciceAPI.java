package com.muscu.carnetMusculation.dto;

import java.util.List;

public class ExerciceAPI {
	private Long id;
	private String nom;
	private String muscle;
	private List<Long> programs;
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
	public String getMuscle() {
		return muscle;
	}
	public void setMuscle(String muscle) {
		this.muscle = muscle;
	}
	public List<Long> getPrograms() {
		return programs;
	}
	public void setPrograms(List<Long> programs) {
		this.programs = programs;
	}

}
