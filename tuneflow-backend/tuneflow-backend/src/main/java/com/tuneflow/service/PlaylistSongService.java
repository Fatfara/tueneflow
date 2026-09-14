package com.tuneflow.service;

import com.tuneflow.Entity.Playlist;
import com.tuneflow.Entity.PlaylistSong;
import com.tuneflow.Entity.Song;
import com.tuneflow.Entity.User;
import com.tuneflow.Repository.PlaylistRepository;
import com.tuneflow.Repository.PlaylistSongRepository;
import com.tuneflow.Repository.SongRepository;
import com.tuneflow.Repository.UserRepository;
import com.tuneflow.dto.PlaylistSongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistSongService {
    private final PlaylistSongRepository playlistSongRepository;
    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final UserRepository userRepository;

    public PlaylistSongResponse addSongToPlaylist(
            Long playlistId,
            Long songId,
            String email) {

        User user = getUser(email);

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        if (!playlist.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You do not own this playlist");
        }

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        if (playlistSongRepository.existsByPlaylistIdAndSongId(
                playlistId, songId)) {

            throw new RuntimeException(
                    "Song already exists in this playlist"
            );
        }

        PlaylistSong playlistSong = PlaylistSong.builder()
                .playlist(playlist)
                .song(song)
                .build();

        PlaylistSong savedPlaylistSong =
                playlistSongRepository.save(playlistSong);

        return mapToResponse(savedPlaylistSong);
    }

    public List<PlaylistSongResponse> getPlaylistSongs(
            Long playlistId,
            String email) {

        User user = getUser(email);

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        if (!playlist.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You do not own this playlist");
        }

        return playlistSongRepository
                .findByPlaylistId(playlistId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void removeSongFromPlaylist(
            Long playlistId,
            Long songId,
            String email) {

        User user = getUser(email);

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        if (!playlist.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You do not own this playlist");
        }

        PlaylistSong playlistSong =
                playlistSongRepository
                        .findByPlaylistIdAndSongId(playlistId, songId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Song not found in playlist"
                                ));

        playlistSongRepository.delete(playlistSong);
    }

    private User getUser(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private PlaylistSongResponse mapToResponse(
            PlaylistSong playlistSong) {

        Song song = playlistSong.getSong();
        Playlist playlist = playlistSong.getPlaylist();

        return PlaylistSongResponse.builder()
                .id(playlistSong.getId())
                .playlistId(playlist.getId())
                .playlistName(playlist.getName())
                .songId(song.getId())
                .songTitle(song.getTitle())
                .artistId(song.getArtist().getId())
                .artistName(song.getArtist().getName())
                .addedAt(playlistSong.getAddedAt())
                .build();
    }
}
