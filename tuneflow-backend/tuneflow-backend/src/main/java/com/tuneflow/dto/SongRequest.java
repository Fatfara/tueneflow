package com.tuneflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongRequest {
    @NotBlank(message = "Song title is required")
    private String title;

    private String audioUrl;

    private String coverImage;

    @NotNull(message = "Duration is required")
    private Integer duration;

    @NotNull(message = "Artist ID is required")
    private Long artistId;

    @NotNull(message = "Genre ID is required")
    private Long genreId;
}
