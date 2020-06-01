package com.spring.projectplanner.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
public class UserModel extends Auditable implements UserDetails {

    @Email(message = "Username needs to be an email!")
    @NotBlank(message = "username is required")
    @Column(unique = true)
    @Getter
    @Setter
    private String username;


    @NotBlank(message = "Please enter your full name!")
    @Getter
    @Setter
    private String fullName;

    @NotBlank(message = "Password is required!")
    @Getter
    @Setter
    private String password;

    @Transient
    @Getter
    @Setter
    private String confirmedPassword;

    public UserModel() {
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
