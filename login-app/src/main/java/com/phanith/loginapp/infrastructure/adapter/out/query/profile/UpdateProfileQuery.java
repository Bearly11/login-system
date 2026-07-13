package com.phanith.loginapp.infrastructure.adapter.out.query.profile;

import com.phanith.command.exception.NotFoundException;
import com.phanith.loginapp.application.port.out.profile.UpdateProfileDb;
import com.phanith.loginapp.domain.User;
import com.phanith.loginapp.infrastructure.adapter.out.database.Users;
import com.phanith.loginapp.infrastructure.adapter.out.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateProfileQuery implements UpdateProfileDb {
    private final UserRepository userRepository;

    @Override
    public void updateProfile(User user) {
        Users users = userRepository.findByEmail(user.getEmail())
                .orElseThrow(()->
                        new NotFoundException("User not found with email: "+user.getEmail()));
        users.setFirstName(user.getFirstName());
        users.setLastName(user.getLastName());
        users.setGender(user.getGender());
        users.setUpdateAt(user.getUpdatedAt());
        userRepository.save(users);
    }
}
