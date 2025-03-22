package com.att.tdp.popcorn_palace.services;

import com.att.tdp.popcorn_palace.domain.dto.ShowtimeDto;
import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;

import java.util.Optional;
/**
 * Service interface for managing showtimes.
 */
public interface ShowtimeService {
    /**
     * Creates a new showtime.
     *
     * @param showtimeDto the showtime data transfer object
     * @return the created showtime data transfer object
     */
    ShowtimeDto createShowtime(ShowtimeDto showtimeDto);
    /**
     * Retrieves a showtime by its ID.
     *
     * @param id the ID of the showtime
     * @return an optional containing the showtime entity if found, or empty if not found
     */
    Optional<ShowtimeEntity> getShowtimeById(Long id);
    /**
     * Fully updates a showtime by its ID.
     *
     * @param id the ID of the showtime to update
     * @param showtimeDto the showtime data transfer object with updated information
     */
    void fullUpdateShowtime(Long id, ShowtimeDto showtimeDto);
    /**
     * Deletes a showtime by its ID.
     *
     * @param id the ID of the showtime to delete
     */
    void deleteShowtime(Long id);
}
