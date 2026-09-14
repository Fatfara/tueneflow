package com.tuneflow.controller;


import com.tuneflow.dto.AlbumRequest;
import com.tuneflow.dto.AlbumResponse;
import com.tuneflow.service.AlbumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<AlbumResponse> createAlbum(
            @Valid @RequestBody AlbumRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(albumService.createAlbum(request));
    }

    @GetMapping
    public ResponseEntity<List<AlbumResponse>> getAllAlbums() {

        return ResponseEntity.ok(
                albumService.getAllAlbums()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumResponse> getAlbumById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                albumService.getAlbumById(id)
        );
    }

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<AlbumResponse>> getAlbumsByArtist(
            @PathVariable Long artistId) {

        return ResponseEntity.ok(
                albumService.getAlbumsByArtist(artistId)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<AlbumResponse>> searchAlbums(
            @RequestParam String title) {

        return ResponseEntity.ok(
                albumService.searchAlbums(title)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponse> updateAlbum(
            @PathVariable Long id,
            @Valid @RequestBody AlbumRequest request) {

        return ResponseEntity.ok(
                albumService.updateAlbum(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(
            @PathVariable Long id) {

        albumService.deleteAlbum(id);

        return ResponseEntity.noContent().build();
    }
}
