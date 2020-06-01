package com.spring.projectplanner.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "backlog")
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Integer PTSequence = 0;
    @Getter
    @Setter
    private String projectIdentifier;

    @Getter
    @Setter
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER )
    @JoinColumn(name="project_id", nullable = false)
    private Project project;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "backlog", orphanRemoval = true)
    @JsonManagedReference
    private List<ProjectTask> projectTasks = new ArrayList<>();

    public Backlog() {
    }
}
