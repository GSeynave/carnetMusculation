package com.muscu.carnetMusculation.entities;

import java.util.Date;
import java.util.Set;

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
@Table(name="programme")
public class Programme {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="nom", length=50, nullable=false, unique=false)
	private String nom;

	@Temporal(TemporalType.DATE)
	@Column(name="date_creation")
	private Date dateCreation;
	
	@OneToMany(mappedBy = "programme")
	private Set<Entrainement> entrainements;
	
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

	public Set<Entrainement> getEntrainements() {
		return entrainements;
	}

	public void setEntrainements(Set<Entrainement> entrainements) {
		this.entrainements = entrainements;
	}

}
