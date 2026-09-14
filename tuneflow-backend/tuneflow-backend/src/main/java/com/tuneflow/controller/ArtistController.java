package com.tuneflow.controller;

import com.tuneflow.dto.ArtistRequest;
import com.tuneflow.dto.ArtistResponse;
import com.tuneflow.service.ArtistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @PostMapping
    public ResponseEntity<ArtistResponse> createArtist(
            @Valid @RequestBody ArtistRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(artistService.createArtist(request));
    }

    @GetMapping
    public ResponseEntity<List<ArtistResponse>> getAllArtists() {

        return ResponseEntity.ok(
                artistService.getAllArtists()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponse> getArtistById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                artistService.getArtistById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistResponse> updateArtist(
            @PathVariable Long id,
            @Valid @RequestBody ArtistRequest request) {

        return ResponseEntity.ok(
                artistService.updateArtist(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(
            @PathVariable Long id) {

        artistService.deleteArtist(id);

        return ResponseEntity.noContent().build();
    }
}
