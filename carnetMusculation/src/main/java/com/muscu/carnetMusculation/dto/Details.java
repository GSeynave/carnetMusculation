package com.muscu.carnetMusculation.dto;

public class Details {

	ExerciceAPI exercice;
	String nbRep;
	long nbSerie;
	String recup;
	
	public ExerciceAPI getExercice() {
		return exercice;
	}
	public void setExercice(ExerciceAPI exercice) {
		this.exercice = exercice;
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
