package com.tuneflow.dto;

import com.tuneflow.Entity.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserResponse {

    private Long id;

    private String name;

    private String email;

    private Role role;

    private String profileImage;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}