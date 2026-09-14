package com.tuneflow.controller;

import com.tuneflow.dto.PlaylistSongRequest;
import com.tuneflow.dto.PlaylistSongResponse;
import com.tuneflow.service.PlaylistSongService;
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
public class PlaylistSongController {
    private final PlaylistSongService playlistSongService;

    @PostMapping("/{playlistId}/songs")
    public ResponseEntity<PlaylistSongResponse> addSongToPlaylist(
            @PathVariable Long playlistId,
            @Valid @RequestBody PlaylistSongRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        playlistSongService.addSongToPlaylist(
                                playlistId,
                                request.getSongId(),
                                email
                        )
                );
    }

    @GetMapping("/{playlistId}/songs")
    public ResponseEntity<List<PlaylistSongResponse>> getPlaylistSongs(
            @PathVariable Long playlistId,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                playlistSongService.getPlaylistSongs(
                        playlistId,
                        email
                )
        );
    }

    @DeleteMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<Void> removeSongFromPlaylist(
            @PathVariable Long playlistId,
            @PathVariable Long songId,
            Authentication authentication) {

        String email = authentication.getName();

        playlistSongService.removeSongFromPlaylist(
                playlistId,
                songId,
                email
        );

        return ResponseEntity.noContent().build();
    }
}
