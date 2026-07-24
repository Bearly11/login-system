package com.phanith.logintask.application.port.in;

import com.phanith.logintask.application.dto.TaskUpdateRequest;

import java.util.UUID;

public interface UpdateTask {
    void update(UUID id, TaskUpdateRequest request);
}
