package com.example.springjwt.authController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springjwt.config.JwtService;
import com.example.springjwt.entity.Role;
import com.example.springjwt.entity.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springjwt.repository.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder().firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        User savedUser = userRepository.save(user);
        Map<String, Object> userClaims = new HashMap<String, Object>();
        userClaims.put("iss", "springjwt");
        userClaims.put("aud", "springjwt");

        System.out.println(savedUser);

        // String jwtToken = jwtService.generateToken(userClaims, user);

        return AuthenticationResponse.builder().token("jwtToken").build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        User user = this.userRepository.findByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(null, user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
