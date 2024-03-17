package com.nhnacademy.team.dooray.repository;

import com.nhnacademy.team.dooray.domain.OnlyAccountId;
import com.nhnacademy.team.dooray.entity.ProjectMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.Pk> {
    @Query("delete from ProjectMember pm where pm.pk.projectId = ?1")
    void deleteProjectMembersByPkProjectId(Integer projectId);

    @Query("select new com.nhnacademy.team.dooray.domain.OnlyAccountId(pm.pk.accountId) " +
            "from ProjectMember pm " +
            "where pm.pk.projectId = ?1")
    List<OnlyAccountId> findAccountIdByProjectId(Integer projectId);
}
