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
@Table(name = "details_exercice")
public class DetailsExercice {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "entrainement_id")
	Entrainement entrainement;
	
	@ManyToOne
	@JoinColumn(name = "exercice_id")
	Exercice exercice;
	
	@Column(name = "nb_serie", nullable = false)
	private String nbSerie;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Entrainement getEntrainement() {
		return entrainement;
	}

	public void setEntrainement(Entrainement entrainement) {
		this.entrainement = entrainement;
	}

	public Exercice getExercice() {
		return exercice;
	}

	public void setExercice(Exercice exercice) {
		this.exercice = exercice;
	}

	public String getNbSerie() {
		return nbSerie;
	}

	public void setNbSerie(String nbSerie) {
		this.nbSerie = nbSerie;
	}

	
}
