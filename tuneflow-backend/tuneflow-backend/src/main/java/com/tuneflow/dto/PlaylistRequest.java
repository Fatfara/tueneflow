package com.tuneflow.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class PlaylistRequest {
    @NotBlank(message = "Playlist name is required")
    private String name;

    private String description;

    private String coverImage;
}
