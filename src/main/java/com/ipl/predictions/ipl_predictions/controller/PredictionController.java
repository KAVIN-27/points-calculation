package com.ipl.predictions.ipl_predictions.controller;

import com.ipl.predictions.ipl_predictions.DTO.Tournament;
import com.ipl.predictions.ipl_predictions.domain.TournamentEntity;
import com.ipl.predictions.ipl_predictions.mapper.Mapper;
import com.ipl.predictions.ipl_predictions.service.PredictionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictionController {

    private PredictionService service;

    private Mapper<TournamentEntity,Tournament> mapper;

    public PredictionController(PredictionService service, Mapper<TournamentEntity, Tournament> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    //to submit the final result
    @PostMapping("result/submit")
    public ResponseEntity<Tournament> postResult(@RequestBody Tournament tournament){
        TournamentEntity tournamentEntity = mapper.mapFrom(tournament);
        TournamentEntity savedTournamentEntity = service.save(tournamentEntity);
        return new ResponseEntity<>(mapper.mapTo(savedTournamentEntity), HttpStatus.CREATED);
    }

    // to get the final result
    @GetMapping("result")
    public ResponseEntity<Tournament> getResult(){
        TournamentEntity foundResult = service.findAll();
        if(foundResult==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mapper.mapTo(foundResult),HttpStatus.OK);
    }
}
