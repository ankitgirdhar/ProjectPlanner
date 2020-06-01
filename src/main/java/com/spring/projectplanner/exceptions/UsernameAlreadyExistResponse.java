package com.spring.projectplanner.exceptions;

import lombok.Getter;
import lombok.Setter;

public class UsernameAlreadyExistResponse {

    @Getter
    @Setter
    private String username;

    public UsernameAlreadyExistResponse(String username) {
        this.username = username;
    }
}
