package com.att.tdp.popcorn_palace.controllers;

import com.att.tdp.popcorn_palace.domain.dto.MovieDto;
import com.att.tdp.popcorn_palace.domain.dto.ShowtimeDto;
import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;
import com.att.tdp.popcorn_palace.services.ShowtimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class ShowtimeController {
    private ShowtimeService showtimeService;


    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @PostMapping(path = "/showtimes")
    public ResponseEntity<?> createShowtime(@RequestBody ShowtimeDto showtime) {
        try {
            ShowtimeDto createdShowtime = this.showtimeService.createShowtime(showtime);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdShowtime);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }

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

    @PutMapping(path = "/showtimes/update/{id}")
    public ResponseEntity<String> fullUpdateShowtime(@PathVariable("id") Long id, @RequestBody ShowtimeDto showtime) {
        try {
            this.showtimeService.fullUpdateShowtime(id, showtime);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
        }
    }

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
