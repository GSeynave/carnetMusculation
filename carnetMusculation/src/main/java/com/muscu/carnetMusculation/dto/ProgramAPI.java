package com.muscu.carnetMusculation.dto;

import java.util.List;

public class ProgramAPI {

	private Long id;
	private String name;
	private String creationDate;
	private List<Long> sessionIds;

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
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public List<Long> getSessionIds() {
		return sessionIds;
	}
	public void setSessionIds(List<Long> sessionIds) {
		this.sessionIds = sessionIds;
	}

}
