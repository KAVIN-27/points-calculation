package com.ipl.predictions.ipl_predictions.mapper.impl;

import com.ipl.predictions.ipl_predictions.DTO.User;
import com.ipl.predictions.ipl_predictions.domain.UserEntity;
import com.ipl.predictions.ipl_predictions.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements Mapper<UserEntity,User> {

    private ModelMapper mapper;

    public UserMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public User mapTo(UserEntity userEntity) {
        return mapper.map(userEntity,User.class);
    }

    @Override
    public UserEntity mapFrom(User user) {
        return mapper.map(user,UserEntity.class);
    }

}
