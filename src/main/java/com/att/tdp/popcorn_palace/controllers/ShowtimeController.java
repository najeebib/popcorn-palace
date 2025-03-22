package com.att.tdp.popcorn_palace.controllers;

import com.att.tdp.popcorn_palace.domain.dto.ShowtimeDto;
import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;
import com.att.tdp.popcorn_palace.services.ShowtimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
/**
 * REST controller for managing showtimes.
 */
@RestController
public class ShowtimeController {
    private ShowtimeService showtimeService;

    /**
     * Constructor for ShowtimeController.
     *
     * @param showtimeService the service to manage showtimes
     */
    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }
    /**
     * Creates a new showtime.
     *
     * @param showtime the showtime to create
     * @return the ResponseEntity with status 201 (Created) and the created showtime in body, or with status 400 (Bad Request) if the showtime is invalid
     */
    @PostMapping(path = "/showtimes")
    public ResponseEntity<?> createShowtime(@RequestBody ShowtimeDto showtime) {
        try {
            ShowtimeDto createdShowtime = this.showtimeService.createShowtime(showtime);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdShowtime);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }
    /**
     * Retrieves a showtime by its ID.
     *
     * @param id the ID of the showtime to retrieve
     * @return the ResponseEntity with status 200 (OK) and the showtime in body, or with status 404 (Not Found) if the showtime is not found
     */
    @GetMapping(path = "/showtimes/{id}")
    public ResponseEntity<ShowtimeDto> getShowtimeById(@PathVariable("id") Long id) {
        Optional<ShowtimeEntity> showtime = this.showtimeService.getShowtimeById(id);

        if (showtime.isPresent()) {
            ShowtimeEntity value = showtime.get();
            ShowtimeDto dto = ShowtimeDto.builder()
                    .movieId(value.getMovie().getId())
                    .theater(value.getTheater())
                    .price(value.getPrice())
                    .startTime(value.getStartTime())
                    .endTime(value.getEndTime())
                    .build();

            return ResponseEntity.ok(dto);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Showtime with id " + id + " not found");
        }
    }
    /**
     * Updates an existing showtime.
     *
     * @param id the ID of the showtime to update
     * @param showtime the showtime to update
     * @return the ResponseEntity with status 200 (OK) or with status 404 (Not Found) if the showtime is not found
     */
    @PostMapping(path = "/showtimes/update/{id}")
    public ResponseEntity<String> fullUpdateShowtime(@PathVariable("id") Long id, @RequestBody ShowtimeDto showtime) {
        try {
            this.showtimeService.fullUpdateShowtime(id, showtime);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
        }
    }
    /**
     * Deletes a showtime.
     *
     * @param id the ID of the showtime to delete
     * @return the ResponseEntity with status 200 (OK) or with status 404 (Not Found) if the showtime is not found
     */
    @DeleteMapping(path = "/showtimes/{id}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable("id") Long id) {
        try {
            this.showtimeService.deleteShowtime(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }
    }
}
