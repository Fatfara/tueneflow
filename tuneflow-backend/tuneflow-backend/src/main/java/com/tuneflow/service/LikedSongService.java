package com.tuneflow.service;

import com.tuneflow.Entity.LikedSong;
import com.tuneflow.Entity.Song;
import com.tuneflow.Entity.User;
import com.tuneflow.Repository.LikedSongRepository;
import com.tuneflow.Repository.SongRepository;
import com.tuneflow.Repository.UserRepository;
import com.tuneflow.dto.LikedSongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikedSongService {

    private final LikedSongRepository likedSongRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public LikedSongResponse likeSong(
            String email,
            Long songId) {

        User user = getUser(email);

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        if (likedSongRepository.existsByUserIdAndSongId(
                user.getId(), songId)) {

            throw new RuntimeException("Song already liked");
        }

        LikedSong likedSong = LikedSong.builder()
                .user(user)
                .song(song)
                .build();

        LikedSong savedLikedSong =
                likedSongRepository.save(likedSong);

        return mapToResponse(savedLikedSong);
    }

    public void unlikeSong(
            String email,
            Long songId) {

        User user = getUser(email);

        LikedSong likedSong =
                likedSongRepository
                        .findByUserIdAndSongId(
                                user.getId(),
                                songId
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Song is not liked"
                                ));

        likedSongRepository.delete(likedSong);
    }

    public List<LikedSongResponse> getMyLikedSongs(
            String email) {

        User user = getUser(email);

        return likedSongRepository
                .findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public boolean isSongLiked(
            String email,
            Long songId) {

        User user = getUser(email);

        return likedSongRepository.existsByUserIdAndSongId(
                user.getId(),
                songId
        );
    }

    private User getUser(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private LikedSongResponse mapToResponse(
            LikedSong likedSong) {

        Song song = likedSong.getSong();

        return LikedSongResponse.builder()
                .id(likedSong.getId())
                .songId(song.getId())
                .songTitle(song.getTitle())
                .artistId(song.getArtist().getId())
                .artistName(song.getArtist().getName())
                .genreId(song.getGenre().getId())
                .genreName(song.getGenre().getName())
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
                .likedAt(likedSong.getLikedAt())
                .build();
    }
}
