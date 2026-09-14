package com.tuneflow.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistSongRequest {
    @NotNull(message = "Song ID is required")
    private Long songId;
}
