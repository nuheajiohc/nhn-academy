package com.nhnacademy.team.dooray.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id")
    private Integer taskId;
    @Column(name="task_title")
    private String taskTitle;
    @Column(name="task_content")
    private String taskContent;
    @Column(name="register_date")
    private LocalDateTime registerDate;


    @ManyToOne
    @JoinColumn(name="project_id")
    private Projects projects;

    @ManyToOne
    @JoinColumn(name="milestone_id")
    private MileStone mileStone;

}