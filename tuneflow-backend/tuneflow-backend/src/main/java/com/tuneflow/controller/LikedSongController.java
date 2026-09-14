package com.tuneflow.controller;

import com.tuneflow.dto.LikedSongResponse;
import com.tuneflow.service.LikedSongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikedSongController {

    private final LikedSongService likedSongService;

    @PostMapping("/{songId}")
    public ResponseEntity<LikedSongResponse> likeSong(
            @PathVariable Long songId,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(likedSongService.likeSong(email, songId));
    }

    @DeleteMapping("/{songId}")
    public ResponseEntity<Void> unlikeSong(
            @PathVariable Long songId,
            Authentication authentication) {

        String email = authentication.getName();

        likedSongService.unlikeSong(email, songId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<LikedSongResponse>> getMyLikedSongs(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                likedSongService.getMyLikedSongs(email)
        );
    }

    @GetMapping("/{songId}")
    public ResponseEntity<Boolean> isSongLiked(
            @PathVariable Long songId,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                likedSongService.isSongLiked(email, songId)
        );
    }
}
