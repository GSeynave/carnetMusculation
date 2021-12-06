package com.muscu.carnetMusculation.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity 
@Table(name="serie")
public class Serie {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="rep", length=5, nullable=false, unique=false)
	private String rep;

	@Column(name="poids")
	private String poids;

	@Column(name="recup")
	private String recup;
	
	@ManyToOne
	@JoinColumn(name="exercice", nullable=false)
	private Exercice exercice;

	@ManyToOne
	@JoinColumn(name="entrainement", nullable=false)
	private Entrainement entrainement;

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

	public Exercice getExercice() {
		return exercice;
	}

	public void setExercice(Exercice exercice) {
		this.exercice = exercice;
	}

	public Entrainement getEntrainement() {
		return entrainement;
	}

	public void setEntrainement(Entrainement entrainement) {
		this.entrainement = entrainement;
	}
	
}
