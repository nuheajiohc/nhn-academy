package com.nhnacademy.team.dooray.repository;

import com.nhnacademy.team.dooray.entity.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("select t from Task t where t.projects.projectId = ?1")
    List<Task> findTaskByProjectId(Integer projectId);
}
