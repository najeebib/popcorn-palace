package com.att.tdp.popcorn_palace.controllers;

import com.att.tdp.popcorn_palace.mappers.Mapper;
import com.att.tdp.popcorn_palace.domain.dto.ShowtimeDto;
import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;
import com.att.tdp.popcorn_palace.services.ShowtimeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowtimeController {
    private ShowtimeService showtimeService;

    private Mapper<ShowtimeEntity, ShowtimeDto> showtimeMapper;

    public ShowtimeController(ShowtimeService showtimeService, Mapper<ShowtimeEntity, ShowtimeDto> showtimeMapper) {
        this.showtimeService = showtimeService;
        this.showtimeMapper = showtimeMapper;
    }

    @PostMapping(path = "/showtimes")
    public ShowtimeDto createShowtime(@RequestBody ShowtimeDto showtime) {
        ShowtimeEntity showtimeEntity = showtimeMapper.mapFrom(showtime);
        ShowtimeEntity savedShowtimeEntity = this.showtimeService.createShowtime(showtimeEntity);
        return showtimeMapper.mapTo(savedShowtimeEntity);
    }
}
