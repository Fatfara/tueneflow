package com.tuneflow.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaybackResponse {

    private Long songId;

    private String title;

    private String audioUrl;

    private String coverImage;

    private Integer duration;

    private Long artistId;

    private String artistName;

    private Long albumId;

    private String albumTitle;
}
