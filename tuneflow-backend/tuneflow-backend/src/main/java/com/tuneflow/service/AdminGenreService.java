package com.tuneflow.service;

import com.tuneflow.Entity.Genre;
import com.tuneflow.Repository.GenreRepository;
import com.tuneflow.dto.GenreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminGenreService {

    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public List<GenreResponse> getAllGenres() {

        return genreRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public GenreResponse getGenreById(Long genreId) {

        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() ->
                        new RuntimeException("Genre not found"));

        return mapToResponse(genre);
    }

    @Transactional
    public void deleteGenre(Long genreId) {

        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() ->
                        new RuntimeException("Genre not found"));

        genreRepository.delete(genre);
    }

    private GenreResponse mapToResponse(Genre genre) {

        return GenreResponse.builder()
                .id(genre.getId())
                .name(genre.getName())
                .description(genre.getDescription())
                .build();
    }
}
