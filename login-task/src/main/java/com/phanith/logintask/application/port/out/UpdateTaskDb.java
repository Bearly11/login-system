package com.phanith.logintask.application.port.out;

import com.phanith.logintask.domain.Tasks;

import java.util.UUID;

public interface UpdateTaskDb {
    void update(Tasks tasks);
}
