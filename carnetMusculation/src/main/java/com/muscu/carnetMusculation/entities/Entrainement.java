package com.muscu.carnetMusculation.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name="Entrainement")
public class Entrainement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;

	@Column(name="date_creation")
	private LocalDate dateCreation;

	@Column(name="date_modification ")
	private LocalDate dateModification;
	
	@Column(name = "type")
	private String type;

	@Column(name="nom", length=50, nullable=false, unique=true)
	private String nom;
	
	@ManyToOne
	@JoinColumn(name="programme_id", nullable=false)
	private Programme programme;
	
	@OneToMany(mappedBy = "entrainement", cascade = CascadeType.ALL)
	private List<Seance> seances;

	@OneToMany(mappedBy = "entrainement", cascade = CascadeType.ALL)
	private List<Serie> series;

	@OneToMany(mappedBy = "entrainement", cascade = CascadeType.ALL)
	private List<EntrainementExercice> details;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}

	public LocalDate getDateModification() {
		return dateModification;
	}

	public void setDateModification(LocalDate dateModification) {
		this.dateModification = dateModification;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Programme getProgramme() {
		return programme;
	}

	public void setProgramme(Programme programme) {
		this.programme = programme;
	}
}
