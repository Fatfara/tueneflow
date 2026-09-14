package com.tuneflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AlbumRequest {
    @NotBlank(message = "Album title is required")
    private String title;

    private String description;

    private String coverImage;

    private LocalDate releaseDate;

    @NotNull(message = "Artist ID is required")
    private Long artistId;
}
