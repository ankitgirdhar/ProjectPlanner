package com.spring.projectplanner.exceptions;

import lombok.Getter;

public class ProjectNotFoundExceptionResponse {


    @Getter
    private String projectNotFound;

    public ProjectNotFoundExceptionResponse(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }
}
