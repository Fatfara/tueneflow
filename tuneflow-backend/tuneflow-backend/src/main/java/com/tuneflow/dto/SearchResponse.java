package com.tuneflow.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchResponse {

    private List<SongResult> songs;

    private List<ArtistResult> artists;

    private List<AlbumResult> albums;

    private List<GenreResult> genres;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SongResult {

        private Long id;
        private String title;
        private String coverImage;
        private Integer duration;
        private Long artistId;
        private String artistName;
        private Long albumId;
        private String albumTitle;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ArtistResult {

        private Long id;
        private String name;
        private String bio;
        private String profileImage;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AlbumResult {

        private Long id;
        private String title;
        private String description;
        private String coverImage;
        private Long artistId;
        private String artistName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GenreResult {

        private Long id;
        private String name;
        private String description;
    }
}