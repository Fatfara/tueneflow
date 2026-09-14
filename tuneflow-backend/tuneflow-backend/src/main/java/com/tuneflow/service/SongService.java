package com.tuneflow.service;


import com.tuneflow.Entity.Album;
import com.tuneflow.Entity.Artist;
import com.tuneflow.Entity.Genre;
import com.tuneflow.Entity.Song;
import com.tuneflow.Repository.AlbumRepository;
import com.tuneflow.Repository.ArtistRepository;
import com.tuneflow.Repository.GenreRepository;
import com.tuneflow.Repository.SongRepository;
import com.tuneflow.dto.SongRequest;
import com.tuneflow.dto.SongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;
    private final AlbumRepository albumRepository;

    public SongResponse createSong(SongRequest request) {

        Artist artist = artistRepository.findById(request.getArtistId())
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        Genre genre = genreRepository.findById(request.getGenreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        Album album = null;

        if (request.getAlbumId() != null) {
            album = albumRepository.findById(request.getAlbumId())
                    .orElseThrow(() -> new RuntimeException("Album not found"));
        }

        Song song = Song.builder()
                .title(request.getTitle())
                .audioUrl(request.getAudioUrl())
                .coverImage(request.getCoverImage())
                .duration(request.getDuration())
                .artist(artist)
                .genre(genre)
                .album(album)
                .build();

        Song savedSong = songRepository.save(song);

        return mapToResponse(savedSong);
    }

    public List<SongResponse> getAllSongs() {

        return songRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public SongResponse getSongById(Long id) {

        Song song = songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        return mapToResponse(song);
    }

    public List<SongResponse> getSongsByArtist(Long artistId) {

        return songRepository.findByArtistId(artistId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<SongResponse> getSongsByGenre(Long genreId) {

        return songRepository.findByGenreId(genreId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<SongResponse> searchSongs(String title) {

        return songRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public SongResponse updateSong(Long id, SongRequest request) {

        Song song = songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        Artist artist = artistRepository.findById(request.getArtistId())
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        Genre genre = genreRepository.findById(request.getGenreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        Album album = null;

        if (request.getAlbumId() != null) {
            album = albumRepository.findById(request.getAlbumId())
                    .orElseThrow(() -> new RuntimeException("Album not found"));
        }

        song.setTitle(request.getTitle());
        song.setAudioUrl(request.getAudioUrl());
        song.setCoverImage(request.getCoverImage());
        song.setDuration(request.getDuration());
        song.setArtist(artist);
        song.setGenre(genre);
        song.setAlbum(album);

        Song updatedSong = songRepository.save(song);

        return mapToResponse(updatedSong);
    }

    public void deleteSong(Long id) {

        Song song = songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        songRepository.delete(song);
    }

    private SongResponse mapToResponse(Song song) {

        return SongResponse.builder()
                .id(song.getId())
                .title(song.getTitle())
                .audioUrl(song.getAudioUrl())
                .coverImage(song.getCoverImage())
                .duration(song.getDuration())
                .artistId(song.getArtist().getId())
                .artistName(song.getArtist().getName())
                .genreId(song.getGenre().getId())
                .genreName(song.getGenre().getName())
                .albumId(song.getAlbum() != null ? song.getAlbum().getId() : null)
                .albumTitle(song.getAlbum() != null ? song.getAlbum().getTitle() : null)
                .createdAt(song.getCreatedAt())
                .build();
    }
}
