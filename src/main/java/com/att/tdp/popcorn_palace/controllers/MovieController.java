package com.att.tdp.popcorn_palace.controllers;

import com.att.tdp.popcorn_palace.domain.dto.MovieDto;
import com.att.tdp.popcorn_palace.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
/**
 * REST controller for managing movies.
 */
@RestController
public class MovieController {

    private MovieService movieService;

    /**
     * Constructor for MovieController.
     *
     * @param movieService the service to manage movies
     */
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    /**
     * Creates a new movie.
     *
     * @param movie the movie to create
     * @return the ResponseEntity with status 201 (Created) and with body the new movie, or with status 400 (Bad Request) if the movie has already an ID
     */
    @PostMapping(path = "/movies")
    public ResponseEntity<?> createMovie(@RequestBody MovieDto movie) {
        try {
            MovieDto createdMovie = this.movieService.createMovie(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }


    }
    /**
     * Retrieves all movies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of movies in body, or with status 404 (Not Found) if there are no movies
     */
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
    /**
     * Updates an existing movie.
     *
     * @param movieTitle the title of the movie to update
     * @param movie the movie to update
     * @return the ResponseEntity with status 200 (OK) or with status 404 (Not Found) if the movie is not found
     */
    @PostMapping(path = "/movies/update/{movieTitle}")
    public ResponseEntity<Void> fullUpdateMovie(@PathVariable("movieTitle")String movieTitle, @RequestBody MovieDto movie) {
        try {
            this.movieService.fullUpdateMovie(movieTitle ,movie);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }

    }
    /**
     * Deletes a movie.
     *
     * @param movieTitle the title of the movie to delete
     * @return the ResponseEntity with status 200 (OK) or with status 404 (Not Found) if the movie is not found
     */
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
