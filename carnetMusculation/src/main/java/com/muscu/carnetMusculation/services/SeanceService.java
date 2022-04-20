package com.muscu.carnetMusculation.services;

import com.muscu.carnetMusculation.dto.SeanceAPI;
import com.muscu.carnetMusculation.dto.SeanceInformationInit;
import com.muscu.carnetMusculation.entities.Seance;
import com.muscu.carnetMusculation.utils.SeanceState;

public interface SeanceService {
	Seance save(Seance seance);
	Seance saveBySeanceAPI(SeanceAPI seanceApi);
	SeanceInformationInit findSIIByEntrainementIdAndState(Long entrainementId, SeanceState state);
	Seance findByEntrainementIdAndState(Long entrainementId, SeanceState state);
	boolean existsByEntrainementIdAndState(long entrainementId, SeanceState state);

}
