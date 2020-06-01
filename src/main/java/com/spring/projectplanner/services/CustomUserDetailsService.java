package com.spring.projectplanner.services;

import com.spring.projectplanner.models.UserModel;
import com.spring.projectplanner.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(s);

        if(userModel==null) new UsernameNotFoundException("User not found!!");
        return userModel;
    }

    @Transactional
    public UserModel loadUserById(Long id) {
        UserModel userModel = userRepository.getById(id);
        if(userModel==null) new UsernameNotFoundException("User not found!!");
        return userModel;
    }

}
