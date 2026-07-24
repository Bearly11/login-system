package com.phanith.logintask.application.port.out;

import com.phanith.logintask.domain.Status;
import com.phanith.logintask.domain.Tasks;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface GetTaskDb {
    boolean existById(UUID id);
    Page<Tasks> getPagination(int page, int pageSize,String name, Status status);
}
