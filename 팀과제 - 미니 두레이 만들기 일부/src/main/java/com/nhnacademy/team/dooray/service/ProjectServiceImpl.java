package com.nhnacademy.team.dooray.service;

import com.nhnacademy.team.dooray.entity.Projects;
import com.nhnacademy.team.dooray.repository.ProjectRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Projects> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<Projects> getProjectsByAccountId(String accountId) {
        return projectRepository.findProjectsByAccountId(accountId);
    }

//    @Override
//    public Projects getProjectByName(String accountId, String projectName) {
//
//        return projectRepository.findProjectByProjectName(accountId,projectName);
//    }

    @Override
    public Projects getProject(Integer projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    @Override
    public Projects createProject(Projects projects) {
        return projectRepository.save(projects);
    }

    @Override
    public Projects updateProject(Projects projects) {
        return projectRepository.save(projects);
    }

    @Override
    public void deleteProject(Integer projectId) {
        projectRepository.deleteById(projectId);
    }
}
