package com.nhnacademy.team.dooray.controller;

import com.nhnacademy.team.dooray.domain.ProjectNameRequest;
import com.nhnacademy.team.dooray.entity.ProjectMember;
import com.nhnacademy.team.dooray.entity.Projects;
import com.nhnacademy.team.dooray.service.ProjectMemberService;
import com.nhnacademy.team.dooray.service.ProjectService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProjectRestController {
    private final ProjectService projectService;

    private final ProjectMemberService projectMemberService;

    public ProjectRestController(ProjectService projectService, ProjectMemberService projectMemberService) {
        this.projectService = projectService;
        this.projectMemberService = projectMemberService;
    }

    @GetMapping("project")
    public ResponseEntity<List<Projects>> getProjects() {
        try {
            List<Projects> projects = projectService.getProjects();
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Projects> getProjectDetail(@PathVariable Integer projectId) {
        try {
            Projects projects = projectService.getProject(projectId);
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INSUFFICIENT_STORAGE);
        }
    }

    @PostMapping("/project/{accountId}")
    public ResponseEntity<String> createProject(@RequestBody ProjectNameRequest projectRequest,
                                                @PathVariable String accountId) {
        try {
            Projects project = new Projects(null, projectRequest.getProjectName(), "활성");
            Projects project1 = projectService.createProject(project);

            ProjectMember projectMember = new ProjectMember(accountId,project1.getProjectId(),"관리자",project1);
            projectMemberService.createProjectMember(projectMember);

            return new ResponseEntity<>("find complete", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("cannot ProjectName" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/project/{accountId}")
    public ResponseEntity<List<Projects>> getProjectsByAccountId(@PathVariable String accountId){

        List<Projects> projects = new ArrayList<>();
        try{
            log.debug("getProjectList");
            projects = projectService.getProjectsByAccountId(accountId);
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(projects, HttpStatus.FORBIDDEN);
        }
    }



//    @PutMapping("projects/{projectId}")
//    public ResponseEntity<String> updateProject(@RequestBody Projects project) {
//        try {
//            projectService.updateProject(project);
//            return new ResponseEntity<>("update complete", HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>("cannot update" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("projects/{projectId}")
//    public ResponseEntity<String> deleteProject(@PathVariable Integer projectId) {
//        try {
//            projectService.deleteProject(projectId);
//            return new ResponseEntity<>("delete complete", HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>("cannot delete" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
