package com.muscu.carnetMusculation.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="program")
public class Program {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="name", length=50, nullable=false, unique=false)
	private String name;

	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@ManyToMany
	@JoinTable(
			name = "exercice_program",
			joinColumns = @JoinColumn(name = "program_id"), 
			inverseJoinColumns = @JoinColumn(name = "exercice_id"))
	private Set<Exercice> exercices;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Set<Exercice> getExercices() {
		return exercices;
	}

	public void setExercices(Set<Exercice> exercices) {
		this.exercices = exercices;
	}


}
