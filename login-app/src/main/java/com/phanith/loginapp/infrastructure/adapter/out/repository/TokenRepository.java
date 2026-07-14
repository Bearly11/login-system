package com.phanith.loginapp.infrastructure.adapter.out.repository;

import com.phanith.loginapp.infrastructure.adapter.out.database.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Tokens, UUID> {
    Optional<Tokens> findByToken(String token);

    List<Tokens> findAllByUserIdAndExpiredFalseAndRevokedFalse(UUID id);


    void deleteByUser_Id(UUID userId);
}
