package com.muscu.carnetMusculation.entities;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.muscu.carnetMusculation.utils.EntrainementType;

@Entity 
@Table(name="Entrainement")
public class Entrainement {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="date_creation")
	private Date dateCreation;

	@Temporal(TemporalType.DATE)
	@Column(name="date_modification ")
	private Date dateModification;
	
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
	private List<DetailsExercice> details;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
