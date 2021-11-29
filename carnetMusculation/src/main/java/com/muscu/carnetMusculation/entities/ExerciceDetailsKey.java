package com.muscu.carnetMusculation.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExerciceDetailsKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "session_id")
	Long sessionId;
	
	@Column(name = "exercice_id")
	Long exerciceId;

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public Long getExerciceId() {
		return exerciceId;
	}

	public void setExerciceId(Long exerciceId) {
		this.exerciceId = exerciceId;
	}

	
}
