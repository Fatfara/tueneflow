package com.tuneflow.Repository;

import com.tuneflow.Entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song,Long> {
    List<Song> findByArtistId(Long artistId);

    List<Song> findByGenreId(Long genreId);

    List<Song> findByTitleContainingIgnoreCase(String title);
}
