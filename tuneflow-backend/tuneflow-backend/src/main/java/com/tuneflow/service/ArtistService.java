package com.tuneflow.service;

import com.tuneflow.Entity.Artist;
import com.tuneflow.Repository.ArtistRepository;
import com.tuneflow.dto.ArtistRequest;
import com.tuneflow.dto.ArtistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistResponse createArtist(ArtistRequest request) {

        Artist artist = Artist.builder()
                .name(request.getName())
                .bio(request.getBio())
                .profileImage(request.getProfileImage())
                .build();

        Artist savedArtist = artistRepository.save(artist);

        return mapToResponse(savedArtist);
    }

    public List<ArtistResponse> getAllArtists() {

        return artistRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ArtistResponse getArtistById(Long id) {

        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        return mapToResponse(artist);
    }

    public ArtistResponse updateArtist(Long id, ArtistRequest request) {

        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        artist.setName(request.getName());
        artist.setBio(request.getBio());
        artist.setProfileImage(request.getProfileImage());

        Artist updatedArtist = artistRepository.save(artist);

        return mapToResponse(updatedArtist);
    }

    public void deleteArtist(Long id) {

        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

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
