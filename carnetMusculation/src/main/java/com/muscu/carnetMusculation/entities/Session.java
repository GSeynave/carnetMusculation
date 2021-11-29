package com.muscu.carnetMusculation.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity 
@Table(name="session")
public class Session {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="name", length=50, nullable=false, unique=false)
	private String name;

	@Column(name="create_date")
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@Column(name="body_part")
	private String bodyPart;

	@ManyToMany(mappedBy = "sessions")
	private Set<Exercice> exercices;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getBodyPart() {
		return bodyPart;
	}

	public void setBodyPart(String bodyPart) {
		this.bodyPart = bodyPart;
	}

	public Set<Exercice> getExercices() {
		return exercices;
	}

	public void setExercices(Set<Exercice> exercices) {
		this.exercices = exercices;
	}
}
