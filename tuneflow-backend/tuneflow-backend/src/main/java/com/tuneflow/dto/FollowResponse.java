package com.tuneflow.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowResponse {

    private Long id;

    private Long artistId;

    private String artistName;

    private String artistProfileImage;

    private LocalDateTime followedAt;
}