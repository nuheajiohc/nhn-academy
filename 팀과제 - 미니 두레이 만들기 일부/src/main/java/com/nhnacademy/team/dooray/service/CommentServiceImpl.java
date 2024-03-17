package com.nhnacademy.team.dooray.service;

import com.nhnacademy.team.dooray.entity.Comment;
import com.nhnacademy.team.dooray.repository.CommentRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getComments() {
        return null;
    }

    @Override
    public List<Comment> getCommentsInTask(Integer taskId) {
        return commentRepository.findCommentsByTask(taskId);
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
