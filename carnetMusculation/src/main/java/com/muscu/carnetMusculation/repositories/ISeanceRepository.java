package com.muscu.carnetMusculation.repositories;

import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.utils.SeanceState;

public interface ISeanceRepository{
	
	Seance findByEntrainementIdAndState(Long entrainementId, SeanceState state);

	Seance save(Seance seance);
}
