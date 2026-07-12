package com.phanith.loginapp.application.mapper;

import com.phanith.loginapp.application.dtos.login.UserRegisterRequest;
import com.phanith.loginapp.domain.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
   public User toEntity(UserRegisterRequest dto){
       if(dto == null){
           return null;
       }
       User user = new User();
       user.setFirstName(dto.getFirstName());
       user.setLastName(dto.getLastName());
       user.setGender(dto.getGender());
       user.setEmail(dto.getEmail());
       user.setPassword(dto.getPassword());
       return user;
   }

}
