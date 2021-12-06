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
@Table(name="entrainement")
public class Entrainement {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@OneToMany(mappedBy="entrainement")
	private Set<Serie> series;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
