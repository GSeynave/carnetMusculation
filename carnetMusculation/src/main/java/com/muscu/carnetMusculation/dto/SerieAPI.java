package com.muscu.carnetMusculation.dto;

public class SerieAPI {
	
	Long id;
	String rep;
	String poids;
	String recup;
	Long seanceId;
	Long exerciceId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRep() {
		return rep;
	}
	public void setRep(String rep) {
		this.rep = rep;
	}
	public String getPoids() {
		return poids;
	}
	public void setPoids(String poids) {
		this.poids = poids;
	}
	public String getRecup() {
		return recup;
	}
	public void setRecup(String recup) {
		this.recup = recup;
	}
	public Long getSeanceId() {
		return seanceId;
	}
	public void setSeanceId(Long seanceId) {
		this.seanceId = seanceId;
	}
	public Long getExerciceId() {
		return exerciceId;
	}
	public void setExerciceId(Long exerciceId) {
		this.exerciceId = exerciceId;
	}

}
