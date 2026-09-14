package com.tuneflow.service;

import com.tuneflow.Entity.Album;
import com.tuneflow.Entity.Artist;
import com.tuneflow.Repository.AlbumRepository;
import com.tuneflow.Repository.ArtistRepository;
import com.tuneflow.dto.AlbumRequest;
import com.tuneflow.dto.AlbumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public AlbumResponse createAlbum(AlbumRequest request) {

        Artist artist = artistRepository.findById(request.getArtistId())
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        Album album = Album.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .coverImage(request.getCoverImage())
                .releaseDate(request.getReleaseDate())
                .artist(artist)
                .build();

        Album savedAlbum = albumRepository.save(album);

        return mapToResponse(savedAlbum);
    }

    public List<AlbumResponse> getAllAlbums() {

        return albumRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public AlbumResponse getAlbumById(Long id) {

        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found"));

        return mapToResponse(album);
    }

    public List<AlbumResponse> getAlbumsByArtist(Long artistId) {

        return albumRepository.findByArtistId(artistId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<AlbumResponse> searchAlbums(String title) {

        return albumRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public AlbumResponse updateAlbum(Long id, AlbumRequest request) {

        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found"));

        Artist artist = artistRepository.findById(request.getArtistId())
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        album.setTitle(request.getTitle());
        album.setDescription(request.getDescription());
        album.setCoverImage(request.getCoverImage());
        album.setReleaseDate(request.getReleaseDate());
        album.setArtist(artist);

        Album updatedAlbum = albumRepository.save(album);

        return mapToResponse(updatedAlbum);
    }

    public void deleteAlbum(Long id) {

        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found"));

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
