package com.phanith.loginapp.application.mapper;

import com.phanith.loginapp.application.dtos.login.UserRegisterRequest;
import com.phanith.loginapp.domain.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRegisterRequest dto);
}
