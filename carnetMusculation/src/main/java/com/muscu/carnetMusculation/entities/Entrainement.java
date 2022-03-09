package com.muscu.carnetMusculation.entities;

import java.util.Date;
import java.util.Set;

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

	@ManyToOne
	@JoinColumn(name="programme_id", nullable=false)
	private Programme programme;
	
	@OneToMany(mappedBy = "entrainement")
	private Set<Seance> seances;
	
	@ManyToMany
	Set<Exercice> exercices;

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

	public Set<Seance> getSeances() {
		return seances;
	}

	public void setSeances(Set<Seance> seances) {
		this.seances = seances;
	}

	public Set<Exercice> getExercices() {
		return exercices;
	}

	public void setExercices(Set<Exercice> exercices) {
		this.exercices = exercices;
	}
}
