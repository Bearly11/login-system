package com.phanith.logintask.application.port.in;

import com.phanith.logintask.application.dto.TaskRequest;

public interface CreateTask {
    void create(TaskRequest request);
}
