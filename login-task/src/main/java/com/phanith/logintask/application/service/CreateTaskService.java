package com.phanith.logintask.application.service;

import com.phanith.logintask.application.dto.TaskRequest;
import com.phanith.logintask.application.dto.mapper.TaskMapper;
import com.phanith.logintask.application.port.in.CreateTask;
import com.phanith.logintask.application.port.out.CreateTaskDb;
import com.phanith.logintask.domain.Status;
import com.phanith.logintask.domain.Tasks;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateTaskService implements CreateTask {
    private final CreateTaskDb createTaskDb;
    private final TaskMapper taskMapper;
    @Override
    public void create(TaskRequest request) {
        UUID id = UUID.randomUUID();
        Tasks tasks = taskMapper.toTasks(request);
        tasks.setId(id);
        tasks.setStatus(Status.PENDING);
        tasks.setCreateAt(LocalDate.now());
        createTaskDb.save(tasks);
    }
}
