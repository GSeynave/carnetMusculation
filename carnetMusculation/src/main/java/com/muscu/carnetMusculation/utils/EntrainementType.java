package com.muscu.carnetMusculation.utils;

public enum EntrainementType {

	FULL_BODY("FULL_BODY"),
	UPPER_BODY("UPPER_BODY"),
	LOWER_BODY("LOWER_BODY"),
	NON_DEFINI("NON_DEFINI");
	
	private final String value;
	EntrainementType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
