package com.tuneflow.controller;

import com.tuneflow.Entity.Role;
import com.tuneflow.dto.*;
import com.tuneflow.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminUserService adminUserService;
    private final AdminArtistService adminArtistService;
    private final AdminSongService adminSongService;
    private final AdminAlbumService adminAlbumService;
    private final AdminDashboardService adminDashboardService;
    private final AdminGenreService adminGenreService;

    @GetMapping("/test")
    public ResponseEntity<String> adminTest() {

        return ResponseEntity.ok(
                "Admin access verified successfully"
        );
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdminUserResponse>> getAllUsers() {

        return ResponseEntity.ok(
                adminUserService.getAllUsers()
        );
    }

    @GetMapping("/users/role/{role}")
    public ResponseEntity<List<AdminUserResponse>> getUsersByRole(
            @PathVariable Role role) {

        return ResponseEntity.ok(
                adminUserService.getUsersByRole(role)
        );
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<AdminUserResponse> getUserById(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                adminUserService.getUserById(userId)
        );
    }

    @GetMapping("/artists")
    public ResponseEntity<List<ArtistResponse>> getAllArtists() {

        return ResponseEntity.ok(
                adminArtistService.getAllArtists()
        );
    }

    @GetMapping("/artists/{artistId}")
    public ResponseEntity<ArtistResponse> getArtistById(
            @PathVariable Long artistId) {

        return ResponseEntity.ok(
                adminArtistService.getArtistById(artistId)
        );
    }

    @DeleteMapping("/artists/{artistId}")
    public ResponseEntity<String> deleteArtist(
            @PathVariable Long artistId) {

        adminArtistService.deleteArtist(artistId);

        return ResponseEntity.ok(
                "Artist deleted successfully"
        );
    }

    @GetMapping("/songs")
    public ResponseEntity<List<SongResponse>> getAllSongs() {

        return ResponseEntity.ok(
                adminSongService.getAllSongs()
        );
    }

    @GetMapping("/songs/{songId}")
    public ResponseEntity<SongResponse> getSongById(
            @PathVariable Long songId) {

        return ResponseEntity.ok(
                adminSongService.getSongById(songId)
        );
    }

    @DeleteMapping("/songs/{songId}")
    public ResponseEntity<String> deleteSong(
            @PathVariable Long songId) {

        adminSongService.deleteSong(songId);

        return ResponseEntity.ok(
                "Song deleted successfully"
        );
    }

    @GetMapping("/albums")
    public ResponseEntity<List<AlbumResponse>> getAllAlbums() {

        return ResponseEntity.ok(
                adminAlbumService.getAllAlbums()
        );
    }

    @GetMapping("/albums/{albumId}")
    public ResponseEntity<AlbumResponse> getAlbumById(
            @PathVariable Long albumId) {

        return ResponseEntity.ok(
                adminAlbumService.getAlbumById(albumId)
        );
    }

    @DeleteMapping("/albums/{albumId}")
    public ResponseEntity<String> deleteAlbum(
            @PathVariable Long albumId) {

        adminAlbumService.deleteAlbum(albumId);

        return ResponseEntity.ok(
                "Album deleted successfully"
        );
    }

    @GetMapping("/genres")
    public ResponseEntity<List<GenreResponse>> getAllGenres() {

        return ResponseEntity.ok(
                adminGenreService.getAllGenres()
        );
    }

    @GetMapping("/genres/{genreId}")
    public ResponseEntity<GenreResponse> getGenreById(
            @PathVariable Long genreId) {

        return ResponseEntity.ok(
                adminGenreService.getGenreById(genreId)
        );
    }

    @DeleteMapping("/genres/{genreId}")
    public ResponseEntity<String> deleteGenre(
            @PathVariable Long genreId) {

        adminGenreService.deleteGenre(genreId);

        return ResponseEntity.ok(
                "Genre deleted successfully"
        );
    }

    @GetMapping("/dashboard")
    public ResponseEntity<AdminDashboardResponse> getDashboardStats() {

        return ResponseEntity.ok(
                adminDashboardService.getDashboardStats()
        );
    }
}