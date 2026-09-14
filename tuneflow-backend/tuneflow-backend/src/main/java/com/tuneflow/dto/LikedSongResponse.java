package com.tuneflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class LikedSongResponse {

    private Long id;

    private Long songId;
    private String songTitle;

    private Long artistId;
    private String artistName;

    private Long genreId;
    private String genreName;

    private Long albumId;
    private String albumTitle;

    private String coverImage;
    private Integer duration;

    private LocalDateTime likedAt;
}