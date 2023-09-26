package com.example.springjwt.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserServiceInterface {

    public UserDetails getAuthUserName(String userEmail);
}
