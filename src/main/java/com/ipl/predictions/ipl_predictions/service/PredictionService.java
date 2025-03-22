package com.ipl.predictions.ipl_predictions.service;

import com.ipl.predictions.ipl_predictions.domain.TournamentEntity;

public interface PredictionService {
    TournamentEntity save(TournamentEntity tournamentEntity);

    TournamentEntity findAll();
}
