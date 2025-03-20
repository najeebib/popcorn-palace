package com.att.tdp.popcorn_palace.services;

import com.att.tdp.popcorn_palace.domain.dto.ShowtimeDto;
import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;

public interface ShowtimeService {
    ShowtimeDto createShowtime(ShowtimeDto showtimeDto);
}
