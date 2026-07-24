package com.phanith.logintask.application.dto;

import com.phanith.logintask.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskUpdateRequest {
    private Status status;
}
