package com.tuneflow.service;


import com.tuneflow.Entity.Playlist;
import com.tuneflow.Entity.User;
import com.tuneflow.Repository.PlaylistRepository;
import com.tuneflow.Repository.UserRepository;
import com.tuneflow.dto.PlaylistRequest;
import com.tuneflow.dto.PlaylistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    public PlaylistResponse createPlaylist(
            String email,
            PlaylistRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Playlist playlist = Playlist.builder()
                .name(request.getName())
                .description(request.getDescription())
                .coverImage(request.getCoverImage())
                .user(user)
                .build();

        Playlist savedPlaylist = playlistRepository.save(playlist);

        return mapToResponse(savedPlaylist);
    }

    public List<PlaylistResponse> getMyPlaylists(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return playlistRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PlaylistResponse getPlaylistById(
            Long id,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        if (!playlist.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You do not own this playlist");
        }

        return mapToResponse(playlist);
    }

    public PlaylistResponse updatePlaylist(
            Long id,
            String email,
            PlaylistRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        if (!playlist.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You do not own this playlist");
        }

        playlist.setName(request.getName());
        playlist.setDescription(request.getDescription());
        playlist.setCoverImage(request.getCoverImage());

        Playlist updatedPlaylist = playlistRepository.save(playlist);

        return mapToResponse(updatedPlaylist);
    }

    public void deletePlaylist(
            Long id,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        if (!playlist.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You do not own this playlist");
        }

        playlistRepository.delete(playlist);
    }

    private PlaylistResponse mapToResponse(Playlist playlist) {

        return PlaylistResponse.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .description(playlist.getDescription())
                .coverImage(playlist.getCoverImage())
                .userId(playlist.getUser().getId())
                .userName(playlist.getUser().getName())
                .createdAt(playlist.getCreatedAt())
                .updatedAt(playlist.getUpdatedAt())
                .build();
    }
}
