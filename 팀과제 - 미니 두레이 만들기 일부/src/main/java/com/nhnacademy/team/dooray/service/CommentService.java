package com.nhnacademy.team.dooray.service;

import com.nhnacademy.team.dooray.entity.Comment;
import java.util.List;

public interface CommentService {
    List<Comment> getComments();

    List<Comment> getCommentsInTask(Integer taskId);

    Comment createComment(Comment comment);
//
//    Comment getComment(Integer commentId);
//
//    Comment updateComment(Comment comment);
//
//    void deleteComment(Integer commentId);
}
