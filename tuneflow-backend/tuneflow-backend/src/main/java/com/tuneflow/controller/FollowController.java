package com.tuneflow.controller;

import com.tuneflow.dto.FollowResponse;
import com.tuneflow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{artistId}")
    public ResponseEntity<FollowResponse> followArtist(
            @PathVariable Long artistId,
            Authentication authentication) {

        FollowResponse response = followService.followArtist(
                authentication.getName(),
                artistId
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{artistId}")
    public ResponseEntity<String> unfollowArtist(
            @PathVariable Long artistId,
            Authentication authentication) {

        followService.unfollowArtist(
                authentication.getName(),
                artistId
        );

        return ResponseEntity.ok("Artist unfollowed successfully");
    }

    @GetMapping
    public ResponseEntity<List<FollowResponse>> getMyFollowing(
            Authentication authentication) {

        List<FollowResponse> following =
                followService.getMyFollowing(
                        authentication.getName()
                );

        return ResponseEntity.ok(following);
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<FollowResponse> getFollowStatus(
            @PathVariable Long artistId,
            Authentication authentication) {

        FollowResponse response =
                followService.getFollowStatus(
                        authentication.getName(),
                        artistId
                );

        return ResponseEntity.ok(response);
    }
}