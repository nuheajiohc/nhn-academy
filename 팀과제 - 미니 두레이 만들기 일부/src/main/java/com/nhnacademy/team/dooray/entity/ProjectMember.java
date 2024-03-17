package com.nhnacademy.team.dooray.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
@Entity
@Table(name = "project_member")
public class ProjectMember {
    @EmbeddedId
    private Pk pk;

    private String authority;

    @MapsId("projectId")
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects projects;

    public ProjectMember(String accountId, Integer projectId,String authority,Projects projects){
        this.pk = new Pk(accountId,projectId);
        this.authority = authority;
        this.projects =projects;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "account_id")
        private String accountId;

        @Column(name = "project_id")
        private Integer projectId;
    }
}