package com.muscu.carnetMusculation.dto;

public class Details {

	Long exerciceId;
	String nom;
	String nbRep;
	long nbSerie;
	String recup;
	
	
	public Long getExerciceId() {
		return exerciceId;
	}
	public void setExerciceId(Long exerciceId) {
		this.exerciceId = exerciceId;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNbRep() {
		return nbRep;
	}
	public void setNbRep(String string) {
		this.nbRep = string;
	}
	public long getNbSerie() {
		return nbSerie;
	}
	public void setNbSerie(long nbSerie) {
		this.nbSerie = nbSerie;
	}
	public String getRecup() {
		return recup;
	}
	public void setRecup(String recup) {
		this.recup = recup;
	}
	
}
