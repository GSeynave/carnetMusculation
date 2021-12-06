package com.muscu.carnetMusculation.dto;

import java.util.List;

public class SerieLineAPI {
	String date;
	List<String> rep;
	List<String> poids;
	List<String> recup;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<String> getRep() {
		return rep;
	}
	public void setRep(List<String> rep) {
		this.rep = rep;
	}
	public List<String> getPoids() {
		return poids;
	}
	public void setPoids(List<String> poids) {
		this.poids = poids;
	}
	public List<String> getRecup() {
		return recup;
	}
	public void setRecup(List<String> recup) {
		this.recup = recup;
	}
	
}
