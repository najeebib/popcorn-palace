package com.att.tdp.popcorn_palace.controllers;

import com.att.tdp.popcorn_palace.domain.dto.MovieDto;
import com.att.tdp.popcorn_palace.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MovieController {

    private MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(path = "/movies")
    public ResponseEntity<?> createMovie(@RequestBody MovieDto movie) {
        try {
            MovieDto createdMovie = this.movieService.createMovie(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }


    }

    @GetMapping(path = "/movies/all")
    public ResponseEntity<?>getAllMovies() {
        try {
            List<MovieDto> movies = this.movieService.getAllMovies();
            if (movies.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No movies found.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(movies);
        } catch (ResponseStatusException ex) {
            // Return an appropriate message and status code
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }

    @PutMapping(path = "/movies/update/{movieTitle}")
    public ResponseEntity<Void> fullUpdateMovie(@PathVariable("movieTitle")String movieTitle, @RequestBody MovieDto movie) {
        try {
            this.movieService.fullUpdateMovie(movieTitle ,movie);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }

    }

    @DeleteMapping(path = "/movies/{movieTitle}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("movieTitle")String movieTitle) {
        try {
            this.movieService.deleteMovie(movieTitle);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }

    }
}
