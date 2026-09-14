package com.tuneflow.service;

import com.tuneflow.Entity.Album;
import com.tuneflow.Entity.Artist;
import com.tuneflow.Entity.Genre;
import com.tuneflow.Entity.Song;
import com.tuneflow.Repository.AlbumRepository;
import com.tuneflow.Repository.ArtistRepository;
import com.tuneflow.Repository.GenreRepository;
import com.tuneflow.Repository.SongRepository;
import com.tuneflow.dto.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public SearchResponse search(String query) {

        String keyword = query == null ? "" : query.trim();

        if (keyword.isEmpty()) {
            throw new RuntimeException("Search query cannot be empty");
        }

        return SearchResponse.builder()
                .songs(
                        songRepository
                                .findByTitleContainingIgnoreCase(keyword)
                                .stream()
                                .map(this::mapSong)
                                .toList()
                )
                .artists(
                        artistRepository
                                .findByNameContainingIgnoreCase(keyword)
                                .stream()
                                .map(this::mapArtist)
                                .toList()
                )
                .albums(
                        albumRepository
                                .findByTitleContainingIgnoreCase(keyword)
                                .stream()
                                .map(this::mapAlbum)
                                .toList()
                )
                .genres(
                        genreRepository
                                .findByNameContainingIgnoreCase(keyword)
                                .stream()
                                .map(this::mapGenre)
                                .toList()
                )
                .build();
    }

    private SearchResponse.SongResult mapSong(Song song) {

        return SearchResponse.SongResult.builder()
                .id(song.getId())
                .title(song.getTitle())
                .coverImage(song.getCoverImage())
                .duration(song.getDuration())
                .artistId(song.getArtist().getId())
                .artistName(song.getArtist().getName())
                .albumId(
                        song.getAlbum() != null
                                ? song.getAlbum().getId()
                                : null
                )
                .albumTitle(
                        song.getAlbum() != null
                                ? song.getAlbum().getTitle()
                                : null
                )
                .build();
    }

    private SearchResponse.ArtistResult mapArtist(Artist artist) {

        return SearchResponse.ArtistResult.builder()
                .id(artist.getId())
                .name(artist.getName())
                .bio(artist.getBio())
                .profileImage(artist.getProfileImage())
                .build();
    }

    private SearchResponse.AlbumResult mapAlbum(Album album) {

        return SearchResponse.AlbumResult.builder()
                .id(album.getId())
                .title(album.getTitle())
                .description(album.getDescription())
                .coverImage(album.getCoverImage())
                .artistId(album.getArtist().getId())
                .artistName(album.getArtist().getName())
                .build();
    }

    private SearchResponse.GenreResult mapGenre(Genre genre) {

        return SearchResponse.GenreResult.builder()
                .id(genre.getId())
                .name(genre.getName())
                .description(genre.getDescription())
                .build();
    }
}