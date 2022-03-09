package com.muscu.carnetMusculation.dto;

import java.util.List;

public class SeanceAPI {
	private Long id;
	private String dateEntrainement;
	private Long entrainementId;
	private List<Long> series;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDateEntrainement() {
		return dateEntrainement;
	}
	public void setDateEntrainement(String dateEntrainement) {
		this.dateEntrainement = dateEntrainement;
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
