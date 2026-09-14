package com.tuneflow.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecentlyPlayedResponse {

    private Long id;

    private Long songId;

    private String songTitle;

    private Long artistId;

    private String artistName;

    private Long albumId;

    private String albumTitle;

    private String coverImage;

    private Integer duration;

    private LocalDateTime playedAt;
}
