package com.muscu.carnetMusculation.utils;

public enum SeanceState {
	INIT (0),
	STARTED (1),
	CANCELLED (2),
	FINISHED (3);

	SeanceState(int code) {
		this.code = code;
	}

	private int code;
	
	public static SeanceState getSeanceStateByCode(int code) {
		for(SeanceState seanceState : SeanceState.values()) {
			if(code == (seanceState.getCode())) {
				return seanceState;
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
