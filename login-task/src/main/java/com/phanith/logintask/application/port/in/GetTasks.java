package com.phanith.logintask.application.port.in;

import com.phanith.logintask.application.dto.PageResponse;
import com.phanith.logintask.application.dto.TaskResponse;
import com.phanith.logintask.domain.Status;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface GetTasks {
    PageResponse<TaskResponse> getPagination(int page, int pageSize, String name, Status status);
}
