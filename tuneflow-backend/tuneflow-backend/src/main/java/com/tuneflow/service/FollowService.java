package com.tuneflow.service;

import com.tuneflow.Entity.Artist;
import com.tuneflow.Entity.Follow;
import com.tuneflow.Entity.User;
import com.tuneflow.Repository.ArtistRepository;
import com.tuneflow.Repository.FollowRepository;
import com.tuneflow.Repository.UserRepository;
import com.tuneflow.dto.FollowResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;

    @Transactional
    public FollowResponse followArtist(String email, Long artistId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        if (followRepository.existsByUserIdAndArtistId(
                user.getId(), artistId)) {
            throw new RuntimeException("Artist already followed");
        }

        Follow follow = Follow.builder()
                .user(user)
                .artist(artist)
                .build();

        return mapToResponse(followRepository.save(follow));
    }

    @Transactional
    public void unfollowArtist(String email, Long artistId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!followRepository.existsByUserIdAndArtistId(
                user.getId(), artistId)) {
            throw new RuntimeException("Artist is not followed");
        }

        followRepository.deleteByUserIdAndArtistId(
                user.getId(), artistId);
    }

    @Transactional(readOnly = true)
    public List<FollowResponse> getMyFollowing(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return followRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public FollowResponse getFollowStatus(
            String email,
            Long artistId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Follow follow = followRepository
                .findByUserIdAndArtistId(user.getId(), artistId)
                .orElseThrow(() -> new RuntimeException(
                        "Artist is not followed"));

        return mapToResponse(follow);
    }

    private FollowResponse mapToResponse(Follow follow) {

        Artist artist = follow.getArtist();

        return FollowResponse.builder()
                .id(follow.getId())
                .artistId(artist.getId())
                .artistName(artist.getName())
                .artistProfileImage(artist.getProfileImage())
                .followedAt(follow.getFollowedAt())
                .build();
    }
}