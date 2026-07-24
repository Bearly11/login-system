package com.phanith.logintask.application.port.out;

import java.util.UUID;

public interface DeleteTaskDb {
    void delete(UUID id);
}
