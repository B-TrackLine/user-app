package com.trackline.fitrockr.userservice.handler.mapper;

import com.trackline.fitrockr.userservice.db.entity.User;
import com.trackline.fitrockr.userservice.model.UserReadModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserReadModel toModel(User userEntity);
}
