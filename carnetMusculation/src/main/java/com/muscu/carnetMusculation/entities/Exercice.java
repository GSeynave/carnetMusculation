package com.muscu.carnetMusculation.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity 
@Table(name="exercice")
public class Exercice {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="nom", length=50, nullable=false, unique=false)
	private String nom;

	@Column(name="date_creation")
	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	@Column(name="muscle_cible")
	private String muscleCible;

	@ManyToMany(mappedBy = "exercices", fetch = FetchType.EAGER)
	private Set<Seance> seances;
	
	@OneToMany(mappedBy = "exercice")
	private Set<Serie> series;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getMuscleCible() {
		return muscleCible;
	}

	public void setMuscleCible(String muscleCible) {
		this.muscleCible = muscleCible;
	}

	public Set<Seance> getSeances() {
		return seances;
	}

	public void setSeances(Set<Seance> seances) {
		this.seances = seances;
	}

	public Set<Serie> getSeries() {
		return series;
	}

	public void setSeries(Set<Serie> series) {
		this.series = series;
	}
	
}
