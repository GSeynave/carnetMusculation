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
@Table(name="exercice")
public class Exercice {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="name", length=50, nullable=false, unique=false)
	private String name;

	@Column(name="create_date")
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@Column(name="muscles")
	private String muscles;

	@OneToMany(mappedBy = "exerciceDetails")
	private Set<ExerciceDetails> details;

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

	public String getMuscles() {
		return muscles;
	}

	public void setMuscles(String muscles) {
		this.muscles = muscles;
	}

	public Set<ExerciceDetails> getDetails() {
		return details;
	}

	public void setDetails(Set<ExerciceDetails> details) {
		this.details = details;
	}
	
}
