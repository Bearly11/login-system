package com.phanith.logintask.infrastructure.adapter.out.query;

import com.phanith.logintask.application.port.out.CreateTaskDb;
import com.phanith.logintask.domain.Tasks;
import com.phanith.logintask.infrastructure.adapter.out.database.Task;
import com.phanith.logintask.infrastructure.adapter.out.respository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Save implements CreateTaskDb {
    private final TaskRepository taskRepository;
    @Override
    public void save(Tasks tasks) {
        var task = new Task();
        task.setId(tasks.getId());
        task.setName(tasks.getName());
        task.setStatus(tasks.getStatus());
        task.setCreateAt(tasks.getCreateAt());
        taskRepository.save(task);
    }
}
