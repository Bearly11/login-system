package com.phanith.logintask.application.port.in;

import com.phanith.logintask.application.dto.TaskRequest;

import java.util.UUID;

public interface DeleteTask {
    void delete(UUID id);
}
