package com.phanith.logintask.application.port.out;

import com.phanith.logintask.domain.Tasks;

public interface CreateTaskDb {
    void save(Tasks tasks);
}
