package com.ipl.predictions.ipl_predictions.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipl.predictions.ipl_predictions.DTO.User;
import com.ipl.predictions.ipl_predictions.domain.UserEntity;
import com.ipl.predictions.ipl_predictions.mapper.Mapper;
import com.ipl.predictions.ipl_predictions.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private UserService service;
    private Mapper<UserEntity,User> mapper;
    private ObjectMapper objectMapper;

    public UserController(Mapper<UserEntity, User> mapper, UserService service,ObjectMapper objectMapper) {
        this.mapper = mapper;
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @PostMapping(path = "user/submit")
    public ResponseEntity<User> submitResponse(@RequestBody User user){
        UserEntity userEntity = mapper.mapFrom(user);
        UserEntity savedUserEntity = service.save(userEntity);
        return new ResponseEntity<>(mapper.mapTo(savedUserEntity), HttpStatus.CREATED);
    }

    //to get a prediction of a particular user
    @GetMapping(path = "user/{username}")
    public ResponseEntity<User> getUserPredictions(@PathVariable("username") String username){
        Optional<UserEntity> foundUser = service.findUser(username);

        return foundUser.map(userEntity ->{
        User user =mapper.mapTo(userEntity);
        return new ResponseEntity<>(user,HttpStatus.OK);
        } ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


    // to get the all users and their predictions
    @GetMapping(path = "user")
    public List<User> getAllPredictions(){
        List<UserEntity> predictions= service.findAllUsers();
        return predictions.stream().map(mapper::mapTo).collect(Collectors.toList());

    }

//    @PatchMapping(path = "user/{username}")
//    public ResponseEntity<User> modifyUser(@PathVariable String username, @RequestBody Map<User,Object> patchPayLoad) {
//
//        UserEntity userEntity = service.findUser(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        User userDto = mapper.mapTo(userEntity);
//
//        User patchedDto = applyPatch(patchPayLoad,userDto);
//
//        UserEntity patchedEntity = mapper.mapFrom(patchedDto);
//
//        UserEntity save = service.save(patchedEntity);
//
//        return ResponseEntity.ok(mapper.mapTo(save) );
//
//    }
//
//    private User applyPatch(Map<User, Object> patchPayLoad, User userDto) {
//
//        ObjectNode dtoNode = objectMapper.convertValue(userDto, ObjectNode.class);
//
//        ObjectNode patchNode = objectMapper.convertValue(patchPayLoad,ObjectNode.class);
//
//        dtoNode.setAll(patchNode);
//
//        return objectMapper.convertValue(dtoNode,User.class);
//
//    }

    @DeleteMapping(path = "user/{username}")
    public String deleteUser(@PathVariable String username){

        UserEntity user = service.findUser(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Long userId = user.getId();

        service.deleteById(userId);

        return "Deleted the user - "+username;

    }


}
