package com.phanith.logintask.infrastructure.adapter.out.query;

import com.phanith.command.exception.NotFoundException;
import com.phanith.logintask.application.port.out.DeleteTaskDb;
import com.phanith.logintask.infrastructure.adapter.out.database.Task;
import com.phanith.logintask.infrastructure.adapter.out.respository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Delete implements DeleteTaskDb {
    private final TaskRepository taskRepository;

    @Override
    public void delete(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Task not found!"));
        taskRepository.delete(task);
    }
}
