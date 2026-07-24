package com.phanith.logintask.infrastructure.adapter.out.database;

import com.phanith.logintask.domain.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="task")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {
    @Id
    @Column(name = "task_id", nullable = false,updatable = false)
    private UUID id;
    @Column(name = "task_name",nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "create_at")
    private LocalDate updateAt;
}
