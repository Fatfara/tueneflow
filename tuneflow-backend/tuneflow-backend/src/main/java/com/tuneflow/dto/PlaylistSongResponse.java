package com.tuneflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class PlaylistSongResponse {
    private Long id;

    private Long playlistId;
    private String playlistName;

    private Long songId;
    private String songTitle;

    private Long artistId;
    private String artistName;

    private LocalDateTime addedAt;
}
