package com.muscu.carnetMusculation.dto;

import java.util.List;

public class SessionAPI {
	private Long id;
	private String name;
	private String creationDate;
	private String bodyPart;
	private Long programId;
	private List<Long> detailsIds;

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
	public String getBodyPart() {
		return bodyPart;
	}
	public void setBodyPart(String bodyPart) {
		this.bodyPart = bodyPart;
	}
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public List<Long> getDetailsIds() {
		return detailsIds;
	}
	public void setDetailsIds(List<Long> detailsIds) {
		this.detailsIds = detailsIds;
	}
	
}
