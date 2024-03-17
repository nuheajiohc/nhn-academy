package com.nhnacademy.team.dooray.controller;

import com.nhnacademy.team.dooray.domain.TaskRequest;
import com.nhnacademy.team.dooray.entity.Projects;
import com.nhnacademy.team.dooray.entity.Task;
import com.nhnacademy.team.dooray.service.ProjectService;
import com.nhnacademy.team.dooray.service.TaskService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskRestController {
    private final TaskService taskService;
    private final ProjectService projectService;

    public TaskRestController(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }



    @GetMapping("task/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable Integer projectId){
        try{
            List<Task> tasks = taskService.getTasksByProjectId(projectId);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/taskDetail/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Integer taskId){
        try {
            Task task = taskService.getTask(taskId);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("tasks")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<String> createTask(@RequestBody Task task) {
//        try {
//            taskService.createTask(task);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//    }

    @PostMapping("task/{projectId}")
    public ResponseEntity<String> createTask(@RequestBody TaskRequest taskRequest, @PathVariable Integer projectId){
        try{
            Projects projects = projectService.getProject(projectId);

            Task task = new Task(null,taskRequest.getTaskTitle(),taskRequest.getTaskContent(), LocalDateTime.now(),projects,null);
            taskService.createTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

}
