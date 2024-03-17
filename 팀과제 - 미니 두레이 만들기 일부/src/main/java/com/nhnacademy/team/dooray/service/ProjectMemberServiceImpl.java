package com.nhnacademy.team.dooray.service;


import com.nhnacademy.team.dooray.domain.OnlyAccountId;
import com.nhnacademy.team.dooray.entity.ProjectMember;
import com.nhnacademy.team.dooray.repository.ProjectMemberRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService{
    private final ProjectMemberRepository projectMemberRepository;

    public ProjectMemberServiceImpl(ProjectMemberRepository projectMemberRepository) {
        this.projectMemberRepository = projectMemberRepository;
    }


    @Override
    public ProjectMember createProjectMember(ProjectMember projectMember) {
        return projectMemberRepository.save(projectMember);
    }

    @Override
    public void deleteProjectMember(Integer projectId) {
        projectMemberRepository.deleteProjectMembersByPkProjectId(projectId);
    }

    @Override
    public List<OnlyAccountId> getAccountIdByProjectId(Integer projectId) {
        return projectMemberRepository.findAccountIdByProjectId(projectId);
    }
}