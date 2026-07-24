package com.phanith.logintask.application.dto.mapper;

import com.phanith.logintask.application.dto.PageResponse;
import com.phanith.logintask.application.dto.TaskRequest;
import com.phanith.logintask.application.dto.TaskResponse;
import com.phanith.logintask.application.dto.TaskUpdateRequest;
import com.phanith.logintask.domain.Tasks;
import com.phanith.logintask.infrastructure.adapter.out.database.Task;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.function.Function;

@Component
@Builder
public class TaskMapper {
    public Tasks toDomain(Task entity) {
        if (entity == null) {
            return null;
        }
        Tasks tasks = new Tasks();
        tasks.setId(entity.getId());
        tasks.setName(entity.getName());
        tasks.setStatus(entity.getStatus());
        tasks.setCreateAt(entity.getCreateAt());
        tasks.setUpdateAt(entity.getUpdateAt());
        return tasks;
    }

    public TaskResponse toTaskResponse(Tasks tasks) {
        if (tasks == null) {
            return null;
        }
        return new TaskResponse(
                tasks.getId(),
                tasks.getName(),
                tasks.getStatus(),
                tasks.getCreateAt(),
                tasks.getUpdateAt()
        );

    }
    public Tasks toTasks(TaskRequest dto) {
        if (dto == null) {
            return null;
        }
        Tasks tasks = new Tasks();
        tasks.setName(dto.getName());
        return tasks;
    }
    public Tasks toTaskUpdate(TaskUpdateRequest dto) {
        if (dto == null) {
            return null;
        }
        Tasks tasks = new Tasks();
        tasks.setStatus(dto.getStatus());
        return tasks;
    }
    //pagination
    public <E,D>PageResponse<D> from(Page<E> page, Function<E,D> dto) {
        if (page == null) {
            return null;
        }
        List<D> content = page.getContent()
                .stream()
                .map(dto)
                .toList();
        return new PageResponse<>(
                content,
                page.getNumber() +1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()

        );
    }
}
