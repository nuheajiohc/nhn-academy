package com.nhnacademy.team.dooray.service;

import com.nhnacademy.team.dooray.entity.Projects;
import java.util.List;

public interface ProjectService {
    List<Projects> getProjects();

    List<Projects> getProjectsByAccountId(String accountId);

//    Projects getProjectByName(String accountId, String projectName);

    Projects getProject(Integer projectId);

    Projects createProject(Projects projects);

    Projects updateProject(Projects projects);

    void deleteProject(Integer projectId);
}
