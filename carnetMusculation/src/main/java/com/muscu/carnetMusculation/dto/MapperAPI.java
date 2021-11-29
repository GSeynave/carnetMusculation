package com.muscu.carnetMusculation.dto;

import java.time.ZoneId;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.muscu.carnetMusculation.entities.Exercice;
import com.muscu.carnetMusculation.entities.Program;
import com.muscu.carnetMusculation.entities.Session;

@Component
public class MapperAPI {

	static ZoneId defaultZoneId = ZoneId.systemDefault();

	@Autowired
	private  ModelMapper mapper;

	//Program mapper
	public ProgramAPI convertToDto(Program program) {
		return mapper.map(program, ProgramAPI.class);
	}

	public Program convertToEntity(ProgramAPI programApi) {
		return mapper.map(programApi, Program.class);
	}

	//Exercice mapper
	public ExerciceAPI convertToDto(Exercice exercice) {
		return mapper.map(exercice, ExerciceAPI.class);
	}

	public Exercice convertToEntity(ExerciceAPI exerciceApi) {
		return mapper.map(exerciceApi, Exercice.class);
	}

	//Session mapper
	public SessionAPI convertToDto(Session session) {
		return mapper.map(session, SessionAPI.class);
	}

	public Session convertToEntity(SessionAPI sessionApi) {
		return mapper.map(sessionApi, Session.class);
	}
}

