package com.tuneflow.service;

import com.tuneflow.Entity.Artist;
import com.tuneflow.Repository.ArtistRepository;
import com.tuneflow.dto.ArtistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminArtistService {

    private final ArtistRepository artistRepository;

    @Transactional(readOnly = true)
    public List<ArtistResponse> getAllArtists() {

        return artistRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ArtistResponse getArtistById(Long artistId) {

        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() ->
                        new RuntimeException("Artist not found"));

        return mapToResponse(artist);
    }

    @Transactional
    public void deleteArtist(Long artistId) {

        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() ->
                        new RuntimeException("Artist not found"));

        artistRepository.delete(artist);
    }

    private ArtistResponse mapToResponse(Artist artist) {

        return ArtistResponse.builder()
                .id(artist.getId())
                .name(artist.getName())
                .bio(artist.getBio())
                .profileImage(artist.getProfileImage())
                .createdAt(artist.getCreatedAt())
                .build();
    }
}
