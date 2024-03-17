package com.nhnacademy.team.dooray.service;

import com.nhnacademy.team.dooray.entity.Task;
import com.nhnacademy.team.dooray.repository.TaskRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByProjectId(Integer projectId) {
        return taskRepository.findTaskByProjectId(projectId);
    }

    @Override
    public Task getTask(Integer taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Integer taskId) {
        taskRepository.deleteById(taskId);
    }
}
