package com.ipl.predictions.ipl_predictions.controller;

import com.ipl.predictions.ipl_predictions.DTO.User;
import com.ipl.predictions.ipl_predictions.service.PointsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public  class PointsController {

    private final PointsService service;


    public PointsController(PointsService service) {
        this.service = service;
    }
    @GetMapping(path = "/points")
    public ResponseEntity<List<User>> getPoints(){
        List<User> users = service.calculatePoints();
        return ResponseEntity.ok(users);
    }


}
