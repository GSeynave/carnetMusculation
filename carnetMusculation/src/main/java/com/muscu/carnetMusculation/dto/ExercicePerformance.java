package com.muscu.carnetMusculation.dto;

import java.util.List;

public class ExercicePerformance {
	String exercice;
	List<Performance> performance;
	public String getExercice() {
		return exercice;
	}
	public void setExercice(String exercice) {
		this.exercice = exercice;
	}
	public List<Performance> getPerformance() {
		return performance;
	}
	public void setPerformance(List<Performance> performance) {
		this.performance = performance;
	}
}
