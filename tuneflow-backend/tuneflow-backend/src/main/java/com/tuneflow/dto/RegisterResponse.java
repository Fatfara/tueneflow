package com.tuneflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RegisterResponse {
    private Long id;
    private String name;
    private String email;
    private String role;
}
