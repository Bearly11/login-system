package com.phanith.logintask.infrastructure.adapter.out.respository;

import com.phanith.logintask.domain.Status;
import com.phanith.logintask.domain.Tasks;
import com.phanith.logintask.infrastructure.adapter.out.database.Task;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID>, JpaSpecificationExecutor<Task> {
    static Specification<Task> hasName(String name) {
        return null;
    }
    static Specification<Task> hasStatus(Status status){
        return (root, query, cb) -> {
            if (status == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("status"), status);
        };
    }

    Task findTaskById(UUID id);
}
