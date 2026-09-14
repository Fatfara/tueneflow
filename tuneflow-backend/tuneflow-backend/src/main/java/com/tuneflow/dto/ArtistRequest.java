package com.tuneflow.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistRequest {
    @NotBlank(message = "Artist name is required")
    private String name;

    private String bio;

    private String profileImage;
}
