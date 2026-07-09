package com.phanith.loginapp.infrastructure.adapter.out.repository;

import com.phanith.loginapp.domain.User;
import com.phanith.loginapp.infrastructure.adapter.out.database.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {
    boolean existsByEmail(String email);

    Optional<Users> findByEmail(String  email);
}
