package com.att.tdp.popcorn_palace.controllers;

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
    public ShowtimeDto createShowtime(@RequestBody ShowtimeDto showtime) {
        return this.showtimeService.createShowtime(showtime);
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
}
