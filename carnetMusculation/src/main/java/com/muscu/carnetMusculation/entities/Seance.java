package com.muscu.carnetMusculation.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.muscu.carnetMusculation.utils.SeanceState;

@Entity
@Table(name = "seance")
public class Seance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "date_entrainement")
	private LocalDate dateEntrainement;

	@Column(name = "seance_state")
	private SeanceState state;

	@ManyToOne
	@JoinColumn(name = "entrainement_id", nullable = false)
	private Entrainement entrainement;

	@OneToMany(mappedBy = "seance")
	private List<Serie> series;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateEntrainement() {
		return dateEntrainement;
	}

	public void setDateEntrainement(LocalDate dateEntrainement) {
		this.dateEntrainement = dateEntrainement;
	}

	public SeanceState getState() {
		return state;
	}

	public void setState(SeanceState state) {
		this.state = state;
	}

	public Entrainement getEntrainement() {
		return entrainement;
	}

	public void setEntrainement(Entrainement entrainement) {
		this.entrainement = entrainement;
	}
}
