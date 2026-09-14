package com.tuneflow.Repository;

import com.tuneflow.Entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findByUserId(Long userId);

    Optional<Follow> findByUserIdAndArtistId(
            Long userId,
            Long artistId
    );

    boolean existsByUserIdAndArtistId(
            Long userId,
            Long artistId
    );

    void deleteByUserIdAndArtistId(
            Long userId,
            Long artistId
    );
}
