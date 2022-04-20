package com.muscu.carnetMusculation.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Programme")
public class Programme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;

	@Column(name = "nom", length = 50, nullable = false, unique = false)
	private String nom;

	@Column(name = "date_creation")
	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	@Column(name = "date_modification")
	@Temporal(TemporalType.DATE)
	private Date dateModification;

	@OneToMany(mappedBy = "programme")
	private List<Entrainement> entrainements;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}
}
