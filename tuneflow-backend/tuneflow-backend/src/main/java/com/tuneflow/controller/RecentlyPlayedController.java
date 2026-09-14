package com.tuneflow.controller;

import com.tuneflow.dto.RecentlyPlayedResponse;
import com.tuneflow.service.RecentlyPlayedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recently-played")
@RequiredArgsConstructor
public class RecentlyPlayedController {

    private final RecentlyPlayedService recentlyPlayedService;

    @PostMapping("/{songId}")
    public ResponseEntity<RecentlyPlayedResponse> addRecentlyPlayed(
            @PathVariable Long songId,
            Authentication authentication) {

        RecentlyPlayedResponse response =
                recentlyPlayedService.addRecentlyPlayed(
                        authentication.getName(),
                        songId
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RecentlyPlayedResponse>> getMyRecentlyPlayed(
            Authentication authentication) {

        List<RecentlyPlayedResponse> recentlyPlayed =
                recentlyPlayedService.getMyRecentlyPlayed(
                        authentication.getName()
                );

        return ResponseEntity.ok(recentlyPlayed);
    }

    @DeleteMapping
    public ResponseEntity<String> clearMyRecentlyPlayed(
            Authentication authentication) {

        recentlyPlayedService.clearMyRecentlyPlayed(
                authentication.getName()
        );

        return ResponseEntity.ok(
                "Recently played history cleared successfully"
        );
    }
}
