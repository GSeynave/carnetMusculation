package com.muscu.carnetMusculation.dto;


public class DetailsExerciceAPI {
	private Long id;
	private String nom;
	private Long entrainementId;
	private ExerciceAPI exercice;
	private String nbSerie;
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
	public Long getEntrainementId() {
		return entrainementId;
	}
	public void setEntrainementId(Long entrainementId) {
		this.entrainementId = entrainementId;
	}
	public ExerciceAPI getExercice() {
		return exercice;
	}
	public void setExercice(ExerciceAPI exercice) {
		this.exercice = exercice;
	}
	public String getNbSerie() {
		return nbSerie;
	}
	public void setNbSerie(String nbSerie) {
		this.nbSerie = nbSerie;
	}
	

}
