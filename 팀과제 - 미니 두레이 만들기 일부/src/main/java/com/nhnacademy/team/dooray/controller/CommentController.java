package com.nhnacademy.team.dooray.controller;

import com.nhnacademy.team.dooray.domain.CommentRequest;
import com.nhnacademy.team.dooray.entity.Comment;
import com.nhnacademy.team.dooray.entity.Task;
import com.nhnacademy.team.dooray.service.CommentService;
import com.nhnacademy.team.dooray.service.TaskService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CommentController {
    private CommentService commentService;
    private TaskService taskService;

    public CommentController(CommentService commentService, TaskService taskService) {
        this.commentService = commentService;
        this.taskService = taskService;
    }

    @GetMapping("/comment/{taskId}")
    public ResponseEntity<List<Comment>> getCommentsInTask(@PathVariable Integer taskId){
        try{
            List<Comment> comments = commentService.getCommentsInTask(taskId);
            return new ResponseEntity<>(comments,HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/comment/{taskId}")
    public ResponseEntity<String> createComment(@PathVariable Integer taskId, @RequestBody CommentRequest commentRequest){
        try{
            log.info(commentRequest.getComment() + commentRequest.getAccountId());
            Task task=taskService.getTask(taskId);
            Comment comment = new Comment(null,commentRequest.getComment(), LocalDateTime.now(),task,commentRequest.getAccountId());
            commentService.createComment(comment);
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
