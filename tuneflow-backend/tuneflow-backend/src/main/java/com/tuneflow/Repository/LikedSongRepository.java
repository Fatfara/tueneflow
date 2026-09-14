package com.tuneflow.Repository;

import com.tuneflow.Entity.LikedSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikedSongRepository extends JpaRepository<LikedSong, Long> {

    List<LikedSong> findByUserId(Long userId);

    Optional<LikedSong> findByUserIdAndSongId(
            Long userId,
            Long songId
    );

    boolean existsByUserIdAndSongId(
            Long userId,
            Long songId
    );

    void deleteByUserIdAndSongId(
            Long userId,
            Long songId
    );
}