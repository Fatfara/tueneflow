package com.tuneflow.service;

import com.tuneflow.Repository.*;
import com.tuneflow.dto.AdminDashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final GenreRepository genreRepository;
    private final PlaylistRepository playlistRepository;

    @Transactional(readOnly = true)
    public AdminDashboardResponse getDashboardStats() {

        return AdminDashboardResponse.builder()
                .totalUsers(userRepository.count())
                .totalArtists(artistRepository.count())
                .totalSongs(songRepository.count())
                .totalAlbums(albumRepository.count())
                .totalGenres(genreRepository.count())
                .totalPlaylists(playlistRepository.count())
                .build();
    }
}
