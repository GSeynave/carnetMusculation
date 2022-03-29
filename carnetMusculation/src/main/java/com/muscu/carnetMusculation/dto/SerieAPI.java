package com.muscu.carnetMusculation.dto;

public class SerieAPI {
	
	Long id;
	String rep;
	float poids;
	String recup;
	Long seanceId;
	Long exerciceId;
	Long entrainementId;
	int numeroSerie;
	
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
	public float getPoids() {
		return poids;
	}
	public void setPoids(float poids) {
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
	public Long getEntrainementId() {
		return entrainementId;
	}
	public void setEntrainementId(Long entrainementId) {
		this.entrainementId = entrainementId;
	}
	public int getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(int numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

}
