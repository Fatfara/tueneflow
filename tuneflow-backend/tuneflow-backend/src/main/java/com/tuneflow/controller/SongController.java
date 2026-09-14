package com.tuneflow.controller;

import com.tuneflow.dto.SongRequest;
import com.tuneflow.dto.SongResponse;
import com.tuneflow.service.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @PostMapping
    public ResponseEntity<SongResponse> createSong(
            @Valid @RequestBody SongRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(songService.createSong(request));
    }

    @GetMapping
    public ResponseEntity<List<SongResponse>> getAllSongs() {

        return ResponseEntity.ok(
                songService.getAllSongs()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongResponse> getSongById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                songService.getSongById(id)
        );
    }

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<SongResponse>> getSongsByArtist(
            @PathVariable Long artistId) {

        return ResponseEntity.ok(
                songService.getSongsByArtist(artistId)
        );
    }

    @GetMapping("/genre/{genreId}")
    public ResponseEntity<List<SongResponse>> getSongsByGenre(
            @PathVariable Long genreId) {

        return ResponseEntity.ok(
                songService.getSongsByGenre(genreId)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<SongResponse>> searchSongs(
            @RequestParam String title) {

        return ResponseEntity.ok(
                songService.searchSongs(title)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongResponse> updateSong(
            @PathVariable Long id,
            @Valid @RequestBody SongRequest request) {

        return ResponseEntity.ok(
                songService.updateSong(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(
            @PathVariable Long id) {

        songService.deleteSong(id);

        return ResponseEntity.noContent().build();
    }
}
