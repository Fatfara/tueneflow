package com.tuneflow.service;

import com.tuneflow.Entity.RecentlyPlayed;
import com.tuneflow.Entity.Song;
import com.tuneflow.Entity.User;
import com.tuneflow.Repository.RecentlyPlayedRepository;
import com.tuneflow.Repository.SongRepository;
import com.tuneflow.Repository.UserRepository;
import com.tuneflow.dto.PlaybackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlaybackService {

    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final RecentlyPlayedRepository recentlyPlayedRepository;


    @Transactional
    public PlaybackResponse getPlaybackInfo(
            Long songId,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() ->
                        new RuntimeException("Song not found"));

        // Record the song as recently played
        RecentlyPlayed recentlyPlayed = RecentlyPlayed.builder()
                .user(user)
                .song(song)
                .build();

        recentlyPlayedRepository.save(recentlyPlayed);

        return PlaybackResponse.builder()
                .songId(song.getId())
                .title(song.getTitle())
                .audioUrl(song.getAudioUrl())
                .coverImage(song.getCoverImage())
                .duration(song.getDuration())
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
                .build();
    }
}