package com.tuneflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ArtistResponse {
    private Long id;
    private String name;
    private String bio;
    private String profileImage;
    private LocalDateTime createdAt;
}
