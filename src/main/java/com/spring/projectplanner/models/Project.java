package com.spring.projectplanner.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "project")
public class Project extends Auditable{

    @Getter
    @Setter
    @NotBlank(message = "Project name is required")
    private String projectName;

    @Getter
    @Setter
    @NotBlank(message = "Project Identifier is required")
    @Size(min=4, max=5, message = "Please use 4 to 5 characters")
    @Column(updatable = false,unique = true)
    private String projectIdentifier;

    @Getter
    @Setter
    @NotBlank(message = "Project description is required")
    private String description;

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date start_date;

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date end_date;


    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL , mappedBy = "project")
    @JsonIgnore
    private Backlog backlog;

    public Project() {
    }



}
