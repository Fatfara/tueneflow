package com.tuneflow.controller;

import com.tuneflow.dto.GenreRequest;
import com.tuneflow.dto.GenreResponse;
import com.tuneflow.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreResponse> createGenre(
            @Valid @RequestBody GenreRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(genreService.createGenre(request));
    }

    @GetMapping
    public ResponseEntity<List<GenreResponse>> getAllGenres() {

        return ResponseEntity.ok(
                genreService.getAllGenres()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponse> getGenreById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                genreService.getGenreById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreResponse> updateGenre(
            @PathVariable Long id,
            @Valid @RequestBody GenreRequest request) {

        return ResponseEntity.ok(
                genreService.updateGenre(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(
            @PathVariable Long id) {

        genreService.deleteGenre(id);

        return ResponseEntity.noContent().build();
    }
}
