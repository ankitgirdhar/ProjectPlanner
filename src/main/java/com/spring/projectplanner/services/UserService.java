package com.spring.projectplanner.services;

import com.spring.projectplanner.exceptions.UsernameAlreadyExistException;
import com.spring.projectplanner.models.UserModel;
import com.spring.projectplanner.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserModel saveUser (UserModel newUser) {

        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            newUser.setUsername(newUser.getUsername());
            newUser.setConfirmedPassword("");
            return userRepository.save(newUser);
        } catch (Exception e) {
            throw new UsernameAlreadyExistException("Username " + newUser.getUsername() + " already exists!!");
        }

    }
}
