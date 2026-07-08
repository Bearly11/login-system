package com.phanith.loginapp.infrastructure.adapter.out.database;

import com.phanith.loginapp.domain.enums.Type;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name="token")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tokens {
    @Id
    @Column(name = "token_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String token;
    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private Type tokenType;

    private boolean expired;
    private boolean revoked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;
}
