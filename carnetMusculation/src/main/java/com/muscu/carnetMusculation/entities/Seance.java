package com.muscu.carnetMusculation.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity 
@Table(name="seance")
public class Seance {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="nom", length=50, nullable=false, unique=false)
	private String nom;

	@Column(name="date_creation")
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@Column(name="muscle_cible")
	private String muscleCible;

	@ManyToOne
	@JoinColumn(name="program_id", nullable=false)
	private Programme programme;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			  name = "seance_exercice", 
			  joinColumns = @JoinColumn(name = "seance_id"), 
			  inverseJoinColumns = @JoinColumn(name = "exercice_id"))
	private Set<Exercice> exercices;

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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getMuscleCible() {
		return muscleCible;
	}

	public void setMuscleCible(String muscleCible) {
		this.muscleCible = muscleCible;
	}

	public Programme getProgramme() {
		return programme;
	}

	public void setProgramme(Programme programme) {
		this.programme = programme;
	}

	public Set<Exercice> getExercices() {
		return exercices;
	}

	public void setExercices(Set<Exercice> exercices) {
		this.exercices = exercices;
	}

}
