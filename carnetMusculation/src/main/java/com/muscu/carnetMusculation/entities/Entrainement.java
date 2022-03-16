package com.muscu.carnetMusculation.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	
	@Column(name = "type")
	private Enum<EntrainementType> type;

	@Column(name="nom", length=50, nullable=false, unique=false)
	private String nom;
	
	@ManyToOne
	@JoinColumn(name="programme_id", nullable=false)
	private Programme programme;
	
	@OneToMany(mappedBy = "entrainement", cascade = CascadeType.ALL)
	private Set<Seance> seances;

	@OneToMany(mappedBy = "entrainement", cascade = CascadeType.ALL)
	private Set<Serie> series;

	@OneToMany(mappedBy = "entrainement", cascade = CascadeType.ALL)
	private Set<DetailsExercice> details;

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

	public Enum<EntrainementType> getType() {
		return type;
	}

	public void setType(Enum<EntrainementType> type) {
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

	public Set<DetailsExercice> getDetails() {
		return details;
	}

	public void setDetails(Set<DetailsExercice> details) {
		this.details = details;
	}


}
