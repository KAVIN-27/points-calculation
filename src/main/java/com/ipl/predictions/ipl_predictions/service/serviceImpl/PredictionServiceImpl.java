package com.ipl.predictions.ipl_predictions.service.serviceImpl;

import com.ipl.predictions.ipl_predictions.domain.TournamentEntity;
import com.ipl.predictions.ipl_predictions.repository.TournamentResultRepository;
import com.ipl.predictions.ipl_predictions.service.PredictionService;
import org.springframework.stereotype.Service;

@Service
public class PredictionServiceImpl implements PredictionService {
    private TournamentResultRepository repository;

    public PredictionServiceImpl(TournamentResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public TournamentEntity save(TournamentEntity tournamentEntity) {
        return repository.save(tournamentEntity);
    }

    @Override
    public TournamentEntity findAll() {
        return repository.findAll().stream().findFirst().orElse(null);
    }
}
