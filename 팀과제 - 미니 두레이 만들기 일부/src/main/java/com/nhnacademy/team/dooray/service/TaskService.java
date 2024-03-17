package com.nhnacademy.team.dooray.service;

import com.nhnacademy.team.dooray.entity.Task;
import java.util.List;

public interface TaskService {

    List<Task> getTasks();

    List<Task> getTasksByProjectId(Integer projectId);

    Task getTask(Integer taskId);

    Task updateTask(Task task);

    Task createTask(Task task);

    void deleteTask(Integer taskId);
}
