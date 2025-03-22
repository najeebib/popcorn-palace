package com.att.tdp.popcorn_palace.services.impl;

import com.att.tdp.popcorn_palace.domain.dto.ShowtimeDto;
import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;
import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;
import com.att.tdp.popcorn_palace.domain.entities.TicketBookingEntity;
import com.att.tdp.popcorn_palace.repositories.MovieRepository;
import com.att.tdp.popcorn_palace.repositories.ShowtimeRepository;
import com.att.tdp.popcorn_palace.repositories.TicketBookingRepository;
import com.att.tdp.popcorn_palace.services.ShowtimeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
/**
 * Service implementation for managing showtimes.
 */
@Service
public class ShowtimeServiceImpl implements ShowtimeService {
    private ShowtimeRepository showtimeRepository;
    private MovieRepository movieRepository;
    private TicketBookingRepository ticketBookingRepository;
    /**
     * Constructs a new ShowtimeServiceImpl with the specified repositories.
     *
     * @param showtimeRepository the showtime repository
     * @param movieRepository the movie repository
     * @param ticketBookingRepository the ticket booking repository
     */
    public ShowtimeServiceImpl(ShowtimeRepository showtimeRepository, MovieRepository movieRepository, TicketBookingRepository ticketBookingRepository) {
        this.showtimeRepository = showtimeRepository;
        this.movieRepository = movieRepository;
        this.ticketBookingRepository = ticketBookingRepository;
    }
    /**
     * Creates a new showtime.
     *
     * @param showtimeDto the showtime data transfer object
     * @return the created showtime data transfer object
     */
    @Override
    public ShowtimeDto createShowtime(ShowtimeDto showtimeDto) {
        Long movieId = showtimeDto.getMovieId();
        MovieEntity movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Movie with id " + movieId + " does not exist"));

        ShowtimeEntity showtime = ShowtimeEntity.builder()
                .movie(movie)
                .theater(showtimeDto.getTheater())
                .price(showtimeDto.getPrice())
                .startTime(showtimeDto.getStartTime())
                .endTime(showtimeDto.getEndTime())
                .build();

        showtimeRepository.save(showtime);
        return  showtimeDto;
    }
    /**
     * Retrieves a showtime by its ID.
     *
     * @param id the ID of the showtime
     * @return an optional containing the showtime entity if found, or empty if not found
     */
    @Override
    public Optional<ShowtimeEntity> getShowtimeById(Long id) {
        return  showtimeRepository.findById(id);
    }
    /**
     * Fully updates a showtime by its ID.
     *
     * @param id the ID of the showtime to update
     * @param showtimeDto the showtime data transfer object with updated information
     */
    @Override
    public void fullUpdateShowtime(Long id, ShowtimeDto showtimeDto) {
        Optional<ShowtimeEntity> showtime = showtimeRepository.findById(id);
        if (showtime.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Showtime with id " + id + " not found");
        }

        Long movieId = showtimeDto.getMovieId();
        MovieEntity movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Movie with id " + movieId + " does not exist"));

        ShowtimeEntity showtimeEntity = showtime.get();
        showtimeEntity.setMovie(movie);
        showtimeEntity.setTheater(showtimeDto.getTheater());
        showtimeEntity.setPrice(showtimeDto.getPrice());
        showtimeEntity.setStartTime(showtimeDto.getStartTime());
        showtimeEntity.setEndTime(showtimeDto.getEndTime());

        showtimeRepository.save(showtimeEntity);
    }
    /**
     * Deletes a showtime by its ID.
     *
     * @param id the ID of the showtime to delete
     */
    @Override
    public void deleteShowtime(Long id) {
        ShowtimeEntity showtime = showtimeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Showtime with id " + id + " not found"));

        List<TicketBookingEntity> bookings = ticketBookingRepository.findByShowtimeId(showtime.getId());

        if (!bookings.isEmpty()) {
            ticketBookingRepository.deleteAll(bookings);
        }

        showtimeRepository.deleteById(id);
    }

}
