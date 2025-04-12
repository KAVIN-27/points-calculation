package com.ipl.predictions.ipl_predictions.service;

import com.ipl.predictions.ipl_predictions.domain.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {


    UserEntity save(UserEntity userEntity);

    Optional<UserEntity> findUser(String username);

    List<UserEntity> findAllUsers();

    void deleteById(Long id);
}
