package com.phanith.logintask.application.dto;

import com.phanith.logintask.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskResponse {
    private UUID id;
    private String name;
    private Status status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
