package com.muscu.carnetMusculation.utils;

import java.time.LocalDate;

import com.muscu.carnetMusculation.entities.Entrainement;
import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.entities.Programme;
import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.entities.Serie;

public class EntityBuilder {

	public static Entrainement entrainementBuilder(LocalDate dateCreation, LocalDate dateModification, String nom,
			EntrainementType entrainementType) {
		Entrainement entrainement = new Entrainement();
		entrainement.setDateCreation(dateCreation);
		entrainement.setDateModification(dateModification);
		entrainement.setNom(nom);
		return entrainement;
	}

	public static Programme programmeBuilder(LocalDate dateCreation, LocalDate dateModification, String nom) {
		Programme programme = new Programme();
		programme.setDateCreation(dateCreation);
		programme.setDateModification(dateModification);
		programme.setNom(nom);
		return programme;
	}

	public static Exercice exerciceBuilder(String nom, String muscle) {
		Exercice exercice = new Exercice();
		exercice.setNom(nom);
		exercice.setMuscle(muscle);
		return exercice;
	}

	public static Seance seanceBuilder(LocalDate date, SeanceState state) {
		Seance seance = new Seance();
		seance.setDateEntrainement(date);
		seance.setState(state);
		return seance;
	}

	public static Serie serieBuilder(String numeroSerie, String poids, String recup, String rep) {
		Serie serie = new Serie();
		serie.setNumeroSerie(numeroSerie);
		serie.setPoids(poids);
		serie.setRecup(recup);
		serie.setRep(rep);
		return serie;
	}

}
