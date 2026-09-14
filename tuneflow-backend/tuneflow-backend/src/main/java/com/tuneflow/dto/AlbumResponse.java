package com.tuneflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class AlbumResponse {
    private Long id;
    private String title;
    private String description;
    private String coverImage;
    private LocalDate releaseDate;

    private Long artistId;
    private String artistName;

    private LocalDateTime createdAt;
}
