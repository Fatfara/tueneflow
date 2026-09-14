package com.tuneflow.Repository;

import com.tuneflow.Entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    List<Playlist> findByUserId(Long userId);

    List<Playlist> findByNameContainingIgnoreCase(String name);
}
