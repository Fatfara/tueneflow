package com.tuneflow.service;

import com.tuneflow.Entity.RecentlyPlayed;
import com.tuneflow.Entity.Song;
import com.tuneflow.Entity.User;
import com.tuneflow.Repository.RecentlyPlayedRepository;
import com.tuneflow.Repository.SongRepository;
import com.tuneflow.Repository.UserRepository;
import com.tuneflow.dto.RecentlyPlayedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecentlyPlayedService {

    private final RecentlyPlayedRepository recentlyPlayedRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    @Transactional
    public RecentlyPlayedResponse addRecentlyPlayed(
            String email,
            Long songId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() ->
                        new RuntimeException("Song not found"));

        RecentlyPlayed recentlyPlayed = RecentlyPlayed.builder()
                .user(user)
                .song(song)
                .build();

        return mapToResponse(
                recentlyPlayedRepository.save(recentlyPlayed)
        );
    }

    @Transactional(readOnly = true)
    public List<RecentlyPlayedResponse> getMyRecentlyPlayed(
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return recentlyPlayedRepository
                .findByUserIdOrderByPlayedAtDesc(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional
    public void clearMyRecentlyPlayed(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        recentlyPlayedRepository.deleteByUserId(user.getId());
    }

    private RecentlyPlayedResponse mapToResponse(
            RecentlyPlayed recentlyPlayed) {

        Song song = recentlyPlayed.getSong();

        return RecentlyPlayedResponse.builder()
                .id(recentlyPlayed.getId())
                .songId(song.getId())
                .songTitle(song.getTitle())
                .artistId(song.getArtist().getId())
                .artistName(song.getArtist().getName())
                .albumId(
                        song.getAlbum() != null
                                ? song.getAlbum().getId()
                                : null
                )
                .albumTitle(
                        song.getAlbum() != null
                                ? song.getAlbum().getTitle()
                                : null
                )
                .coverImage(song.getCoverImage())
                .duration(song.getDuration())
                .playedAt(recentlyPlayed.getPlayedAt())
                .build();
    }
}
