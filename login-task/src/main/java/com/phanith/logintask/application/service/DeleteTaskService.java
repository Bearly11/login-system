package com.phanith.logintask.application.service;

import com.phanith.command.exception.NotFoundException;
import com.phanith.logintask.application.port.in.DeleteTask;
import com.phanith.logintask.application.port.out.DeleteTaskDb;
import com.phanith.logintask.application.port.out.GetTaskDb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class DeleteTaskService implements DeleteTask {
    private final DeleteTaskDb deleteTaskDb;
    private final GetTaskDb getTaskDb;
    @Override
    public void delete(UUID id) {
        if(getTaskDb.existById(id)){
            deleteTaskDb.delete(id);
        }else{
            throw new NotFoundException("Not found");
        }


    }
}
