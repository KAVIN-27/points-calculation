package com.ipl.predictions.ipl_predictions.service.serviceImpl;

import com.ipl.predictions.ipl_predictions.DTO.User;
import com.ipl.predictions.ipl_predictions.domain.TournamentEntity;
import com.ipl.predictions.ipl_predictions.domain.UserEntity;
import com.ipl.predictions.ipl_predictions.mapper.Mapper;
import com.ipl.predictions.ipl_predictions.repository.TournamentResultRepository;
import com.ipl.predictions.ipl_predictions.repository.UserPredictionRepository;
import com.ipl.predictions.ipl_predictions.service.PointsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointsServiceImpl implements PointsService {

    private final UserPredictionRepository userRepo;
    private final TournamentResultRepository tournamentRepo;
    private Mapper<UserEntity,User> mapper;


    public PointsServiceImpl(UserPredictionRepository userRepo, TournamentResultRepository tournamentRepo, Mapper<UserEntity, User> mapper) {
        this.userRepo = userRepo;
        this.tournamentRepo = tournamentRepo;
        this.mapper = mapper;
    }

    @Override
    public List<User> calculatePoints() {
        TournamentEntity actualResult = tournamentRepo.findFirstByOrderByIdDesc()
                .orElseThrow(() -> new RuntimeException("No predictions Found"));

        List<UserEntity> users = userRepo.findAll();

        for(UserEntity user : users){
            int points = 0;
            if(user.getWinner().equalsIgnoreCase(actualResult.getWinner())){
                points +=8;
            }else if(user.getWinner().equalsIgnoreCase(actualResult.getRunnerUp())){
                points +=4;
            }

            if(user.getRunnerUp().equalsIgnoreCase(actualResult.getRunnerUp())){
                points +=6;
            } else if (user.getRunnerUp().equalsIgnoreCase(actualResult.getWinner())) {
                points +=3;
            }

            if(user.getOrangeCap().equalsIgnoreCase(actualResult.getOrangeCap())){
                points +=10;
            }else if(isTopThreeOrangeCap(user.getOrangeCap())){
                points +=7;
            }

            if(user.getPurpleCap().equalsIgnoreCase(actualResult.getPurpleCap())){
                points += 10;
            }else if(isTopThreePurpleCap(user.getPurpleCap())){
                points +=7;
            }

            if (user.getEmergingPlayer().equalsIgnoreCase(actualResult.getEmergingPlayer())) {
                points += 25;
            }

            if(user.getMostValuablePlayer().equalsIgnoreCase(actualResult.getMostValuablePlayer())){
                points += 20;
            } else if (isToThreeMostValuablePlayer(user.getMostValuablePlayer())) {
                    points += 15;
            }


            if (points == (8 + 6 + 10 + 10 + 25+20)) { // Perfect Score Check
                points += 30;
            }
            if (user.getWinner().equalsIgnoreCase(actualResult.getWinner()) && user.getRunnerUp().equalsIgnoreCase(actualResult.getRunnerUp())) {
                points += 10;
            }


            user.setPoints(points);
            userRepo.save(user);
        }

        return users.stream().map(mapper::mapTo).collect(Collectors.toList());
    }

    private boolean isToThreeMostValuablePlayer(String mostValuablePlayer) {
        List<String> topThreePurpleCap = List.of("Varun Chakravathy","Harshith Rana","Sunil Narine");
        return topThreePurpleCap.contains(mostValuablePlayer);
    }

    private boolean isTopThreePurpleCap(String purpleCap) {
        List<String> topThreePurpleCap = List.of("Varun Chakravathy","Harshith Rana","Sunil Narine");
        return topThreePurpleCap.contains(purpleCap);
    }

    private boolean isTopThreeOrangeCap(String orangeCap) {
        List<String> topThreeOrangeCap = List.of("Rahane","Venkatesh Iyer","de Kock");
        return topThreeOrangeCap.contains(orangeCap);
    }
}
