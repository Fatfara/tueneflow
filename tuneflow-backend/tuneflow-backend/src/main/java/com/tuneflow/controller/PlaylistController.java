package com.tuneflow.controller;

import com.tuneflow.dto.PlaylistRequest;
import com.tuneflow.dto.PlaylistResponse;
import com.tuneflow.service.PlaylistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<PlaylistResponse> createPlaylist(
            @Valid @RequestBody PlaylistRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(playlistService.createPlaylist(email, request));
    }

    @GetMapping("/my")
    public ResponseEntity<List<PlaylistResponse>> getMyPlaylists(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                playlistService.getMyPlaylists(email)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistResponse> getPlaylistById(
            @PathVariable Long id,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                playlistService.getPlaylistById(id, email)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaylistResponse> updatePlaylist(
            @PathVariable Long id,
            @Valid @RequestBody PlaylistRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                playlistService.updatePlaylist(id, email, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(
            @PathVariable Long id,
            Authentication authentication) {

        String email = authentication.getName();

        playlistService.deletePlaylist(id, email);

        return ResponseEntity.noContent().build();
    }
}
