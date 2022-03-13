package com.muscu.carnetMusculation.entities;

import java.util.Date;
import java.time.OffsetDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="programme")
@NamedNativeQueries({
	@NamedNativeQuery(name = "programme.deleteById", query = "delete from programme p where p.id = :id"),
	@NamedNativeQuery(name = "programme.findAll", query = "select * from programme p", resultClass=Programme.class),
	@NamedNativeQuery(name = "programme.findById", query = "select * from programme p where p.id = :id", resultClass=Programme.class)
})
public class Programme {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="nom", length=50, nullable=false, unique=false)
	private String nom;
	
	@Column(name="date_creation")
	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	@Column(name="date_modification")
	@Temporal(TemporalType.DATE)
	private Date dateModification;
	
	@OneToMany(mappedBy = "programme", cascade = CascadeType.ALL)
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

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public Set<Entrainement> getEntrainements() {
		return entrainements;
	}

	public void setEntrainements(Set<Entrainement> entrainements) {
		this.entrainements = entrainements;
	}
}
