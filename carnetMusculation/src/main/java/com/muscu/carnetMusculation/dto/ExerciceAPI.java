package com.muscu.carnetMusculation.dto;

import java.util.List;

public class ExerciceAPI {
	private Long id;
	private String nom;
	private String muscle;
	private Long detailId;
	List<Long> serieIds;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getMuscle() {
		return muscle;
	}
	public void setMuscle(String muscle) {
		this.muscle = muscle;
	}
	public Long getDetailId() {
		return detailId;
	}
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	public List<Long> getSerieIds() {
		return serieIds;
	}
	public void setSerieIds(List<Long> serieIds) {
		this.serieIds = serieIds;
	}
	
}
