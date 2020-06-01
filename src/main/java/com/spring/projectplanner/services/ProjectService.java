package com.spring.projectplanner.services;

import com.spring.projectplanner.exceptions.ProjectIdException;
import com.spring.projectplanner.models.Backlog;
import com.spring.projectplanner.models.Project;
import com.spring.projectplanner.repositories.BacklogRepository;
import com.spring.projectplanner.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdateProject(Project project) {

        String identifier = project.getProjectIdentifier().toUpperCase();
        try {

            project.setProjectIdentifier(identifier);

            if(project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(identifier);
            }
            else {
                project.setBacklog(backlogRepository.findByProjectIdentifier(identifier));
            }



            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID" + identifier + " already exists");
        }
    }


    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null ) {
            throw new ProjectIdException("Project " + projectId +  " doesn't exist exists");
        }
        return project;
    }


    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null) {
            throw new ProjectIdException("Cannot find project with ID " + projectId );
        }

        projectRepository.delete(project);
    }
}
