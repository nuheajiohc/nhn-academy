package com.nhnacademy.team.dooray.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
//@Entity
//@Table(name="accounts")
public class Account {
//    @Id
//    @Column(name="account_id")
    private String accountId;
//    @Column(name="account_pw")
    private String accountPw;
    private String email;
    private String name;
//    @Column(name="join_date")
    private LocalDateTime joinDate;
//    @Column(name="last_login_date")
    private LocalDateTime lastLoginDate;

}