package com.ipl.predictions.ipl_predictions.mapper.impl;

import com.ipl.predictions.ipl_predictions.DTO.Tournament;
import com.ipl.predictions.ipl_predictions.domain.TournamentEntity;
import com.ipl.predictions.ipl_predictions.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PredictionMapperImpl implements Mapper<TournamentEntity, Tournament> {

    private ModelMapper mapper;

    public PredictionMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Tournament mapTo(TournamentEntity tournamentEntity) {
        return mapper.map(tournamentEntity,Tournament.class);
    }

    @Override
    public TournamentEntity mapFrom(Tournament tournament) {
        return mapper.map(tournament, TournamentEntity.class);
    }
}
