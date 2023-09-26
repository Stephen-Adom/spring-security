package com.example.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springjwt.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
}
