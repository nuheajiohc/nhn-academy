package com.nhnacademy.team.dooray.repository;

import com.nhnacademy.team.dooray.entity.Projects;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Projects, Integer> {

    @Query("select p from Projects p inner join ProjectMember pm on p.projectId = pm.pk.projectId where pm.pk.accountId = ?1 ")
    List<Projects> findProjectsByAccountId(String accountId);

//    @Query("select p from Projects p where p.projectName = ?1")
//    Projects findProjectByProjectName(String projectName){
//
//    }
}


