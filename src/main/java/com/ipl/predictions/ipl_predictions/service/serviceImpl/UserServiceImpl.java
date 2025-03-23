package com.ipl.predictions.ipl_predictions.service.serviceImpl;

import com.ipl.predictions.ipl_predictions.domain.UserEntity;
import com.ipl.predictions.ipl_predictions.repository.UserPredictionRepository;
import com.ipl.predictions.ipl_predictions.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserPredictionRepository repository;

    public UserServiceImpl(UserPredictionRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return repository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findUser(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return repository.findAll();
    }
}
