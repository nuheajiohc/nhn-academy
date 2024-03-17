package com.nhnacademy.team.dooray.service;

import com.nhnacademy.team.dooray.domain.OnlyAccountId;
import com.nhnacademy.team.dooray.entity.ProjectMember;
import java.util.List;

public interface ProjectMemberService {
    ProjectMember createProjectMember(ProjectMember projectMember);

    void deleteProjectMember(Integer projectId);

    List<OnlyAccountId> getAccountIdByProjectId(Integer projectId);
}
