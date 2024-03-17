package com.nhnacademy.team.dooray.repository;

import com.nhnacademy.team.dooray.entity.Comment;
import com.nhnacademy.team.dooray.entity.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("select c from Comment c where c.task.taskId = ?1")
    List<Comment> findCommentsByTask(Integer taskId);
}