package com.spring.projectplanner.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity(name = "project_task")
public class ProjectTask extends Auditable{

    @Getter
    @Setter
    @Column(updatable = false, unique = true)
    private String projectSequence;

    @Getter
    @Setter
    @NotBlank(message = "Please include a project Summary")
    private String summary;

    @Getter
    @Setter
    private String acceptanceCriteria;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private Integer priority;

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date dueDate;

    @Getter
    @Setter
    @Column(updatable = false)
    private String projectIdentifier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "backlog_id", updatable = false, nullable = false)
    @JsonBackReference
    @Getter
    @Setter
    private Backlog backlog;


    public ProjectTask() {
    }

    @Override
    public String toString() {
        return "ProjectTask{" +
                "projectSequence='" + projectSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", projectIdentifier='" + projectIdentifier + '\'' +
                '}';
    }
}
