package com.phanith.logintask.infrastructure.adapter.out.query;

import com.phanith.command.exception.NotFoundException;
import com.phanith.logintask.application.port.out.UpdateTaskDb;
import com.phanith.logintask.domain.Tasks;
import com.phanith.logintask.infrastructure.adapter.out.database.Task;
import com.phanith.logintask.infrastructure.adapter.out.respository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Update implements UpdateTaskDb {
    private final TaskRepository taskRepository;
    @Override
    public void update(Tasks tasks) {
        Task task = taskRepository.findById(tasks.getId()).
                orElseThrow(()-> new NotFoundException("Task not found"));
        task.setStatus(tasks.getStatus());
        task.setUpdateAt(tasks.getUpdateAt());
        taskRepository.save(task);

    }
}
