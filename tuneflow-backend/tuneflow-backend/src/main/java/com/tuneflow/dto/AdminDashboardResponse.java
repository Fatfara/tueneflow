package com.tuneflow.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDashboardResponse {

    private long totalUsers;

    private long totalArtists;

    private long totalSongs;

    private long totalAlbums;

    private long totalGenres;

    private long totalPlaylists;
}
