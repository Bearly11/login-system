package com.phanith.logintask.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tasks {
    private UUID id;
    private String name;
    private Status status;
    private LocalDate createAt;
    private LocalDate updateAt;

}
