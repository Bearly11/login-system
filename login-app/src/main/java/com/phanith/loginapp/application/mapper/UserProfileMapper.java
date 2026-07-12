package com.phanith.loginapp.application.mapper;

import org.springframework.stereotype.Component;
import com.phanith.loginapp.domain.*;
import com.phanith.loginapp.application.dtos.profile.UserProfileResponse;

@Component
public class UserProfileMapper {
    public UserProfileResponse toProfileResponse(User user) {
    if (user == null) {
        return null;
    }
    return new UserProfileResponse(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getGender(),
            user.getEmail(),
            user.getCreatedAt(),
            user.getUpdatedAt()
    );
}

}
