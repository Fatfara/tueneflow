package com.tuneflow.Repository;

import com.tuneflow.Entity.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Long> {
    List<PlaylistSong> findByPlaylistId(Long playlistId);

    Optional<PlaylistSong> findByPlaylistIdAndSongId(
            Long playlistId,
            Long songId
    );

    boolean existsByPlaylistIdAndSongId(
            Long playlistId,
            Long songId
    );

    void deleteByPlaylistIdAndSongId(
            Long playlistId,
            Long songId
    );
}
