package com.tuneflow.service;

import com.tuneflow.Entity.Song;
import com.tuneflow.Repository.SongRepository;
import com.tuneflow.dto.SongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminSongService {

    private final SongRepository songRepository;

    @Transactional(readOnly = true)
    public List<SongResponse> getAllSongs() {

        return songRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public SongResponse getSongById(Long songId) {

        Song song = songRepository.findById(songId)
                .orElseThrow(() ->
                        new RuntimeException("Song not found"));

        return mapToResponse(song);
    }

    @Transactional
    public void deleteSong(Long songId) {

        Song song = songRepository.findById(songId)
                .orElseThrow(() ->
                        new RuntimeException("Song not found"));

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
                .createdAt(song.getCreatedAt())
                .build();
    }
}
