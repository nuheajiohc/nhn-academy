package com.nhnacademy.team.dooray.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "writer_id")
    private String writerId;
}
