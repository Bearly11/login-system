package com.phanith.logintask.api;

import com.phanith.logintask.application.dto.PageResponse;
import com.phanith.logintask.application.dto.TaskRequest;
import com.phanith.logintask.application.dto.TaskResponse;
import com.phanith.logintask.application.dto.TaskUpdateRequest;
import com.phanith.logintask.application.port.in.CreateTask;
import com.phanith.logintask.application.port.in.DeleteTask;
import com.phanith.logintask.application.port.in.GetTasks;
import com.phanith.logintask.application.port.in.UpdateTask;
import com.phanith.logintask.domain.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/task/")
public class TaskController {
    private final CreateTask createTask;
    private final GetTasks getTask;
    private final UpdateTask updateTask;
    private final DeleteTask deleteTask;

    @GetMapping("")
    public ResponseEntity<PageResponse<TaskResponse>> getTasks(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false)Status status
            ) {
        PageResponse<TaskResponse> response = getTask.getPagination(page, pageSize, name, status);
        return ResponseEntity.ok(response);

    }
    @PostMapping("")
    public ResponseEntity<Void> createTask(@RequestBody TaskRequest taskRequest) {
        createTask.create(taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PatchMapping("{id}")
    public ResponseEntity<Void> updateTask(@PathVariable UUID id, @RequestBody TaskUpdateRequest Request ) {
        updateTask.update(id, Request);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        deleteTask.delete(id);
        return ResponseEntity.noContent().build();
    }

}
