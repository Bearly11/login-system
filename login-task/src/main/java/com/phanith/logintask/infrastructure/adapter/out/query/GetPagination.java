package com.phanith.logintask.infrastructure.adapter.out.query;

import com.phanith.logintask.application.dto.TaskResponse;
import com.phanith.logintask.application.dto.mapper.TaskMapper;
import com.phanith.logintask.application.port.out.GetTaskDb;
import com.phanith.logintask.domain.Status;
import com.phanith.logintask.domain.Tasks;
import com.phanith.logintask.infrastructure.adapter.out.database.Task;
import com.phanith.logintask.infrastructure.adapter.out.respository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.phanith.logintask.infrastructure.adapter.out.respository.TaskRepository.hasName;
import static com.phanith.logintask.infrastructure.adapter.out.respository.TaskRepository.hasStatus;

@Component
@RequiredArgsConstructor
public class GetPagination implements GetTaskDb {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public boolean existById(UUID id) {
        return taskRepository.existsById(id);
    }

    @Override
    public Page<Tasks> getPagination(int page, int pageSize, String name, Status status) {
        Pageable pageable = PageRequest.of(page -1, pageSize);

        Specification<Task> spec =  Specification
                .where(hasName(name))
                .and(hasStatus(status));
        Page<Task>  tasks = taskRepository.findAll(spec, pageable);
        return tasks.map(taskMapper::toDomain);
    }
}
