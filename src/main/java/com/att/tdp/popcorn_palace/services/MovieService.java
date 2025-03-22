package com.att.tdp.popcorn_palace.services;

import com.att.tdp.popcorn_palace.domain.dto.MovieDto;
import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;

import java.util.List;
/**
 * Service interface for managing movies.
 */
public interface MovieService {
    /**
     * Creates a new movie.
     *
     * @param movieEntity the movie data transfer object
     * @return the created movie data transfer object
     */
    MovieDto createMovie(MovieDto movieEntity);
    /**
     * Retrieves all movies.
     *
     * @return a list of movie data transfer objects
     */
    List<MovieDto> getAllMovies();
    /**
     * Checks if a movie exists by its ID.
     *
     * @param id the ID of the movie
     * @return true if the movie exists, false otherwise
     */
    Boolean isExists(Long id);
    /**
     * Fully updates a movie by its title.
     *
     * @param title the title of the movie to update
     * @param movie the movie data transfer object with updated information
     */
    void fullUpdateMovie(String title, MovieDto movie);
    /**
     * Deletes a movie by its title.
     *
     * @param title the title of the movie to delete
     */
    void deleteMovie(String title);
}
