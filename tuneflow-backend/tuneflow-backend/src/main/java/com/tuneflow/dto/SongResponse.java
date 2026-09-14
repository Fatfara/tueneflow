package com.tuneflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class SongResponse {
    private Long id;
    private String title;
    private String audioUrl;
    private String coverImage;
    private Integer duration;

    private Long artistId;
    private String artistName;

    private Long genreId;
    private String genreName;

    private LocalDateTime createdAt;
}
