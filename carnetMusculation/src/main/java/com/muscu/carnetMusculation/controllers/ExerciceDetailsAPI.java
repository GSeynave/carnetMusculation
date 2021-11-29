package com.muscu.carnetMusculation.controllers;

import com.muscu.carnetMusculation.dto.ExerciceAPI;
import com.muscu.carnetMusculation.dto.SessionAPI;

public class ExerciceDetailsAPI {
	
	private Long id;
	private SessionAPI session;
	private ExerciceAPI exercice;
	private String recuptTime;
	private String nbRep;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SessionAPI getSession() {
		return session;
	}
	public void setSession(SessionAPI session) {
		this.session = session;
	}
	public ExerciceAPI getExercice() {
		return exercice;
	}
	public void setExercice(ExerciceAPI exercice) {
		this.exercice = exercice;
	}
	public String getRecuptTime() {
		return recuptTime;
	}
	public void setRecuptTime(String recuptTime) {
		this.recuptTime = recuptTime;
	}
	public String getNbRep() {
		return nbRep;
	}
	public void setNbRep(String nbRep) {
		this.nbRep = nbRep;
	}

}
