package com.phanith.logintask.application.service;

import com.phanith.logintask.application.dto.PageResponse;
import com.phanith.logintask.application.dto.TaskResponse;
import com.phanith.logintask.application.dto.mapper.TaskMapper;
import com.phanith.logintask.application.port.in.GetTasks;
import com.phanith.logintask.application.port.out.GetTaskDb;
import com.phanith.logintask.domain.Status;
import com.phanith.logintask.domain.Tasks;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTaskService implements GetTasks {
    private final GetTaskDb getTaskDb;
    private final TaskMapper taskMapper;

    @Override
    public PageResponse<TaskResponse> getPagination(int page, int pageSize, String name, Status status) {
        Page<Tasks> pages = getTaskDb.getPagination(page, pageSize, name, status);
        return taskMapper.from(pages,taskMapper::toTaskResponse);
    };
}
