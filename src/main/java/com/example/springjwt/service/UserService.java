package com.example.springjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.springjwt.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    public UserDetails getAuthUserName(String userEmail) {
        return this.userRepository.findByEmail(userEmail);
    }
}
