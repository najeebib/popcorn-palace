package com.att.tdp.popcorn_palace.services.impl;

import com.att.tdp.popcorn_palace.domain.dto.MovieDto;
import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;
import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;
import com.att.tdp.popcorn_palace.domain.entities.TicketBookingEntity;
import com.att.tdp.popcorn_palace.repositories.MovieRepository;
import com.att.tdp.popcorn_palace.repositories.ShowtimeRepository;
import com.att.tdp.popcorn_palace.repositories.TicketBookingRepository;
import com.att.tdp.popcorn_palace.services.MovieService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
/**
 * Service implementation for managing movies.
 */
@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private ShowtimeRepository showtimeRepository;
    private TicketBookingRepository ticketBookingRepository;
    /**
     * Constructs a new MovieServiceImpl with the specified repositories.
     *
     * @param movieRepository the movie repository
     * @param showtimeRepository the showtime repository
     * @param ticketBookingRepository the ticket booking repository
     */
    public MovieServiceImpl(MovieRepository movieRepository, ShowtimeRepository showtimeRepository, TicketBookingRepository ticketBookingRepository) {
        this.movieRepository = movieRepository;
        this.showtimeRepository = showtimeRepository;
        this.ticketBookingRepository = ticketBookingRepository;
    }
    /**
     * Creates a new movie.
     *
     * @param movieDto the movie data transfer object
     * @return the created movie data transfer object
     */
    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        MovieEntity movieEntity = MovieEntity.builder()
                .title(movieDto.getTitle())
                .genre(movieDto.getGenre())
                .duration(movieDto.getDuration())
                .rating(movieDto.getRating())
                .releaseYear(movieDto.getReleaseYear())
                .build();
        movieRepository.save(movieEntity);
        return  movieDto;
    }
    /**
     * Retrieves all movies.
     *
     * @return a list of movie data transfer objects
     */
    @Override
    public List<MovieDto> getAllMovies() {
        List<MovieEntity> entities = StreamSupport.stream(movieRepository.findAll().spliterator(), false).toList();

        return entities.stream()
                .map(entity -> MovieDto.builder()
                        .title(entity.getTitle())
                        .genre(entity.getGenre())
                        .rating(entity.getRating())
                        .duration(entity.getDuration())
                        .releaseYear(entity.getReleaseYear())
                        .build())
                .toList();

    }
    /**
     * Checks if a movie exists by its ID.
     *
     * @param id the ID of the movie
     * @return true if the movie exists, false otherwise
     */
    @Override
    public Boolean isExists(Long id) {
        return movieRepository.existsById(id);
    }
    /**
     * Fully updates a movie by its title.
     *
     * @param title the title of the movie to update
     * @param movieDto the movie data transfer object with updated information
     */
    @Override
    public void fullUpdateMovie(String title, MovieDto movieDto) {
        if(movieRepository.findByTitle(title) == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with title " + title + " not found");
        }
        MovieEntity existingMovie = movieRepository.findByTitle(title);

        existingMovie.setTitle(movieDto.getTitle());
        existingMovie.setGenre(movieDto.getGenre());
        existingMovie.setDuration(movieDto.getDuration());
        existingMovie.setRating(movieDto.getRating());
        existingMovie.setReleaseYear(movieDto.getReleaseYear());

        movieRepository.save(existingMovie);
    }
    /**
     * Deletes a movie by its title.
     *
     * @param title the title of the movie to delete
     */
    @Override
    @Transactional
    public void deleteMovie(String title) {
        MovieEntity movie = movieRepository.findByTitle(title);
        if (movie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with title " + title + " not found");
        }

        List<ShowtimeEntity> showtimes = showtimeRepository.findByMovieId(movie.getId());
        if (showtimes.isEmpty()) {
            movieRepository.deleteByTitle(title);
            return;
        }

        for (ShowtimeEntity showtime : showtimes) {
            List<TicketBookingEntity> bookings = ticketBookingRepository.findByShowtimeId(showtime.getId());

            if (!bookings.isEmpty()) {
                ticketBookingRepository.deleteAll(bookings);
            }
        }

        showtimeRepository.deleteAll(showtimes);

        movieRepository.deleteByTitle(title);

    }
}
