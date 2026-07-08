package com.phanith.loginapp.application.mapper;

import com.phanith.loginapp.application.dtos.login.UserRegisterRequest;
import com.phanith.loginapp.domain.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
   public User toEntity(UserRegisterRequest dto){
       if(dto == null){
           return null;
       }
       User user = new User();
       user.setEmail(dto.getEmail());
       user.setPassword(dto.getPassword());
       return user;
   }

}
