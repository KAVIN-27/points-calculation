package com.ipl.predictions.ipl_predictions.controller;

import com.ipl.predictions.ipl_predictions.DTO.User;
import com.ipl.predictions.ipl_predictions.domain.UserEntity;
import com.ipl.predictions.ipl_predictions.mapper.Mapper;
import com.ipl.predictions.ipl_predictions.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private UserService service;
    private Mapper<UserEntity,User> mapper;

    public UserController(Mapper<UserEntity, User> mapper, UserService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping(path = "user/submit")
    public ResponseEntity<User> submitResponse(@RequestBody User user){
        UserEntity userEntity = mapper.mapFrom(user);
        UserEntity savedUserEntity = service.save(userEntity);
        return new ResponseEntity<>(mapper.mapTo(savedUserEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "user/{username}")
    public ResponseEntity<User> getUserPredictions(@PathVariable("username") String username){
        Optional<UserEntity> foundUser = service.findUser(username);

        return foundUser.map(userEntity ->{
        User user =mapper.mapTo(userEntity);
        return new ResponseEntity<>(user,HttpStatus.OK);
        } ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }



}
