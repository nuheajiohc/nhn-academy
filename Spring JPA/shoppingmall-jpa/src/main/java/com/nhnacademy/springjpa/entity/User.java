package com.nhnacademy.springjpa.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "UserID")
    private String userId;

    @Column(name = "UserName", nullable = false)
    private String userName;

    @Column(name = "UserPassword", nullable = false)
    private String userPassword;

    @Column(name = "UserBirth", nullable = false)
    private String userBirth;

    @Column(name = "UserAuth", nullable = false)
    private String userAuth;

    @Column(name = "UserPoint", nullable = false)
    private Integer userPoint;

    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "LatestLoginAt")
    private LocalDateTime latestLoginAt = null;
}
