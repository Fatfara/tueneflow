package com.tuneflow.controller;

import com.tuneflow.dto.PlaybackResponse;
import com.tuneflow.service.PlaybackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/playback")
@RequiredArgsConstructor
public class PlaybackController {

    private final PlaybackService playbackService;
    @GetMapping("/{songId}")
    public ResponseEntity<PlaybackResponse> getPlaybackInfo(
            @PathVariable Long songId,
            Authentication authentication) {

        PlaybackResponse response =
                playbackService.getPlaybackInfo(
                        songId,
                        authentication.getName()
                );

        return ResponseEntity.ok(response);
    }
}
