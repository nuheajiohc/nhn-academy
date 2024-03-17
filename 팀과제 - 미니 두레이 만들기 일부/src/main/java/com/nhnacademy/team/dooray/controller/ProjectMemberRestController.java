package com.nhnacademy.team.dooray.controller;

import com.nhnacademy.team.dooray.domain.OnlyAccountId;
import com.nhnacademy.team.dooray.domain.UserRegisterRequest;
import com.nhnacademy.team.dooray.entity.ProjectMember;
import com.nhnacademy.team.dooray.entity.Projects;
import com.nhnacademy.team.dooray.service.ProjectMemberService;
import com.nhnacademy.team.dooray.service.ProjectService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProjectMemberRestController {
    private final ProjectMemberService projectMemberService;
    private final ProjectService projectService;

    public ProjectMemberRestController(ProjectMemberService projectMemberService, ProjectService projectService) {
        this.projectMemberService = projectMemberService;
        this.projectService = projectService;
    }

    @GetMapping("/member/{projectId}")
    public ResponseEntity<List<OnlyAccountId>> getAccountIds(@PathVariable Integer projectId){
        try {
            List<OnlyAccountId> accountIds = projectMemberService.getAccountIdByProjectId(projectId);
//            log.info(accountIds.get(0).getAccountId());
            return new ResponseEntity<>(accountIds, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/member/register/{projectId}")
    public ResponseEntity<String> createMemberInProject(@RequestBody UserRegisterRequest userRegisterRequest, @PathVariable Integer projectId){
        try{
            Projects project = projectService.getProject(projectId);
            ProjectMember projectMember = new ProjectMember(userRegisterRequest.getAccountId(),projectId,
                    userRegisterRequest.getAuthority(), project);
            projectMemberService.createProjectMember(projectMember);

            return new ResponseEntity<>("put member complete", HttpStatus.CREATED);
        }catch(RuntimeException e){
            return new ResponseEntity<>("fail put member" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}