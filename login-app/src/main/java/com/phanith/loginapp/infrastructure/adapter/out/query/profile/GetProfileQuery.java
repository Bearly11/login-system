package com.phanith.loginapp.infrastructure.adapter.out.query.profile;


import com.phanith.loginapp.infrastructure.adapter.out.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetProfileQuery {
    private final UserRepository userRepository;


}
