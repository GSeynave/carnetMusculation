package com.muscu.carnetMusculation.dto;

public class SerieAPI {
	
	Long id;
	String poids;
	String rep;
	String recup;
	Long exerciceId;
	Long entrainementId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPoids() {
		return poids;
	}
	public void setPoids(String poids) {
		this.poids = poids;
	}
	public String getRep() {
		return rep;
	}
	public void setRep(String rep) {
		this.rep = rep;
	}
	public String getRecup() {
		return recup;
	}
	public void setRecup(String recup) {
		this.recup = recup;
	}
	public Long getExerciceId() {
		return exerciceId;
	}
	public void setExerciceId(Long exerciceId) {
		this.exerciceId = exerciceId;
	}
	public Long getEntrainementId() {
		return entrainementId;
	}
	public void setEntrainementId(Long entrainementId) {
		this.entrainementId = entrainementId;
	}

}
