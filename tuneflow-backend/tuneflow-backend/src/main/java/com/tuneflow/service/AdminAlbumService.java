package com.tuneflow.service;

import com.tuneflow.Entity.Album;
import com.tuneflow.Repository.AlbumRepository;
import com.tuneflow.dto.AlbumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminAlbumService {

    private final AlbumRepository albumRepository;

    @Transactional(readOnly = true)
    public List<AlbumResponse> getAllAlbums() {

        return albumRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public AlbumResponse getAlbumById(Long albumId) {

        Album album = albumRepository.findById(albumId)
                .orElseThrow(() ->
                        new RuntimeException("Album not found"));

        return mapToResponse(album);
    }

    @Transactional
    public void deleteAlbum(Long albumId) {

        Album album = albumRepository.findById(albumId)
                .orElseThrow(() ->
                        new RuntimeException("Album not found"));

        albumRepository.delete(album);
    }

    private AlbumResponse mapToResponse(Album album) {

        return AlbumResponse.builder()
                .id(album.getId())
                .title(album.getTitle())
                .description(album.getDescription())
                .coverImage(album.getCoverImage())
                .releaseDate(album.getReleaseDate())
                .artistId(album.getArtist().getId())
                .artistName(album.getArtist().getName())
                .createdAt(album.getCreatedAt())
                .build();
    }
}
