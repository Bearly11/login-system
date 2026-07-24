package com.phanith.logintask.application.service;

import com.phanith.command.exception.NotFoundException;
import com.phanith.logintask.application.dto.TaskUpdateRequest;
import com.phanith.logintask.application.dto.mapper.TaskMapper;
import com.phanith.logintask.application.port.in.UpdateTask;
import com.phanith.logintask.application.port.out.GetTaskDb;
import com.phanith.logintask.application.port.out.UpdateTaskDb;
import com.phanith.logintask.domain.Tasks;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateTaskService implements UpdateTask {
    private final UpdateTaskDb updateTaskDb;
    private final GetTaskDb getTaskDb;

    @Override
    public void update(UUID id, TaskUpdateRequest status) {
        if(getTaskDb.existById(id)){
            Tasks task = new Tasks();
            task.setStatus(status.getStatus());
            task.setUpdateAt(LocalDate.now());
            updateTaskDb.update(task);
        }else{
            throw new NotFoundException("Not found");
        }


    }
}
