package com.ipl.predictions.ipl_predictions.service;

import com.ipl.predictions.ipl_predictions.DTO.User;

import java.util.List;

public interface PointsService {
    List<User> calculatePoints();
}
