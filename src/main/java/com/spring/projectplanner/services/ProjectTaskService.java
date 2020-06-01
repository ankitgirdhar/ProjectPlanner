package com.spring.projectplanner.services;

import com.spring.projectplanner.exceptions.ProjectNotFoundException;
import com.spring.projectplanner.models.Backlog;
import com.spring.projectplanner.models.Project;
import com.spring.projectplanner.models.ProjectTask;
import com.spring.projectplanner.repositories.BacklogRepository;
import com.spring.projectplanner.repositories.ProjectRepository;
import com.spring.projectplanner.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        try {
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
            projectTask.setBacklog(backlog);
            Integer BacklogSequence = backlog.getPTSequence();
            BacklogSequence++;
            backlog.setPTSequence(BacklogSequence);
            projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            if(projectTask.getPriority() == null )
                projectTask.setPriority(3);

            if(projectTask.getStatus() == null || projectTask.getStatus() == "")
                projectTask.setStatus("TO_DO");

            return projectTaskRepository.save(projectTask);
        } catch(Exception e) {
            throw new ProjectNotFoundException("Project with identifier:" + projectIdentifier + " is not found");
        }

    }

    public Iterable<ProjectTask> findBacklogById(String backlog_id) {

        Project project = projectRepository.findByProjectIdentifier(backlog_id);
        if(project == null)
            throw new ProjectNotFoundException("The project with ID :" + backlog_id + " doesn't exist!");

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String sequence) {

        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if(backlog==null)
            throw new ProjectNotFoundException("Project with ID: " + backlog_id + " is not found!");

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(sequence);
        if(projectTask==null)
            throw new ProjectNotFoundException("Project Task " + sequence + " is not available!!");

        if(!projectTask.getProjectIdentifier().equals(backlog_id) )
            throw new ProjectNotFoundException(" Project Task " + sequence + " does not exist in the backlog: " + backlog_id);


        return projectTask;
    }


    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id) {
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);

        projectTask = updatedTask;
        return projectTaskRepository.save(projectTask);

    }

    public void deletePTByProjectSequence(String backlog_id, String pt_id) {
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id);

        projectTaskRepository.delete(projectTask);
    }
}
