package com.tuneflow.Repository;

import com.tuneflow.Entity.RecentlyPlayed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecentlyPlayedRepository extends JpaRepository<RecentlyPlayed, Long> {

    List<RecentlyPlayed> findByUserIdOrderByPlayedAtDesc(Long userId);

    void deleteByUserId(Long userId);
}