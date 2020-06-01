package com.spring.projectplanner.exceptions;

import lombok.Getter;
import lombok.Setter;

public class InvalidLoginResponse {
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    public InvalidLoginResponse() {
        this.username = "Invalid UserName!";
        this.password = "Invalid Password!";
    }
}
