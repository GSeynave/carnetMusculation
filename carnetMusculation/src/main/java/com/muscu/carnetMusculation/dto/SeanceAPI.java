package com.muscu.carnetMusculation.dto;

import java.time.LocalDate;
import java.util.List;

import com.muscu.carnetMusculation.utils.SeanceState;

public class SeanceAPI {
	private Long id;
	private LocalDate dateEntrainement;
	private SeanceState state;
	private Long entrainementId;
	private List<Long> series;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDateEntrainement() {
		return dateEntrainement;
	}
	public void setDateEntrainement(LocalDate date) {
		this.dateEntrainement = date;
	}
	public SeanceState getstate() {
		return state;
	}
	public void setSeanceState(SeanceState state) {
		this.state = state;
	}
	public Long getEntrainementId() {
		return entrainementId;
	}
	public void setEntrainementId(Long entrainementId) {
		this.entrainementId = entrainementId;
	}
	public List<Long> getSeries() {
		return series;
	}
	public void setSeries(List<Long> series) {
		this.series = series;
	}
}
