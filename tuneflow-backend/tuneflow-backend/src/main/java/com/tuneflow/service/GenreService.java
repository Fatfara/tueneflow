package com.tuneflow.service;

import com.tuneflow.Entity.Genre;
import com.tuneflow.Repository.GenreRepository;
import com.tuneflow.dto.GenreRequest;
import com.tuneflow.dto.GenreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreResponse createGenre(GenreRequest request) {

        if (genreRepository.existsByName(request.getName())) {
            throw new RuntimeException("Genre already exists");
        }

        Genre genre = Genre.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        Genre savedGenre = genreRepository.save(genre);

        return mapToResponse(savedGenre);
    }

    public List<GenreResponse> getAllGenres() {

        return genreRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public GenreResponse getGenreById(Long id) {

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        return mapToResponse(genre);
    }

    public GenreResponse updateGenre(Long id, GenreRequest request) {

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        genre.setName(request.getName());
        genre.setDescription(request.getDescription());

        Genre updatedGenre = genreRepository.save(genre);

        return mapToResponse(updatedGenre);
    }

    public void deleteGenre(Long id) {

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));

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
