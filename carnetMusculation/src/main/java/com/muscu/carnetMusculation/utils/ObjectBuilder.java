package com.muscu.carnetMusculation.utils;

import java.time.LocalDate;

import com.muscu.carnetMusculation.entities.Entrainement;
import com.muscu.carnetMusculation.entities.Programme;

public class ObjectBuilder {
	
	public static Entrainement entrainementBuilder(LocalDate dateCreation, LocalDate dateModification, String nom, EntrainementType entrainementType) {
		Entrainement entrainement = new Entrainement();
		entrainement.setDateCreation(dateCreation);
		entrainement.setDateModification(dateModification);
		entrainement.setNom(nom);
		return entrainement;
	}
	
	public static Programme programmeBuilder(LocalDate dateCreation, LocalDate dateModification, String nom) {
		Programme programme = new Programme ();
		programme.setDateCreation(dateCreation);
		programme.setDateModification(dateModification);
		programme.setNom(nom);
		return programme;
	}
}
