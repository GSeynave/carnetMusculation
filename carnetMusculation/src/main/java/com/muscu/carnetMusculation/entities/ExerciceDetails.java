package com.muscu.carnetMusculation.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class ExerciceDetails {
	
	@EmbeddedId
	private ExerciceDetailsKey id;
	
	@ManyToOne
	@MapsId("sessionId")
	@JoinColumn(name = "session_id")
	private Session sessionDetails;

	@ManyToOne
	@MapsId("exerciceId")
	@JoinColumn(name = "exercice_id")
	private Exercice exerciceDetails;
	
	@Column(name = "recupTime")
	private String recupTime;
	
	@Column(name = "nbRep")
	private String nbRep;

	public ExerciceDetailsKey getId() {
		return id;
	}

	public void setId(ExerciceDetailsKey id) {
		this.id = id;
	}

	public Session getSessionDetails() {
		return sessionDetails;
	}

	public void setSessionDetails(Session sessionDetails) {
		this.sessionDetails = sessionDetails;
	}

	public Exercice getExerciceDetails() {
		return exerciceDetails;
	}

	public void setExerciceDetails(Exercice exerciceDetails) {
		this.exerciceDetails = exerciceDetails;
	}

	public String getRecupTime() {
		return recupTime;
	}

	public void setRecupTime(String recupTime) {
		this.recupTime = recupTime;
	}

	public String getNbRep() {
		return nbRep;
	}

	public void setNbRep(String nbRep) {
		this.nbRep = nbRep;
	}
	
}
