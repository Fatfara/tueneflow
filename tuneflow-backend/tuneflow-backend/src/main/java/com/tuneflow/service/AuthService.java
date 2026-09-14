package com.tuneflow.service;

import com.tuneflow.Entity.Role;
import com.tuneflow.Entity.User;
import com.tuneflow.Repository.UserRepository;
import com.tuneflow.dto.RegisterRequest;
import com.tuneflow.dto.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse registerResponse(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exist");
        }
        User user= User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        User savedUser=userRepository.save(user);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole().name())
                .build();
    }
}
