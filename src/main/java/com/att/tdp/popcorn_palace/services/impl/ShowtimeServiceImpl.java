package com.att.tdp.popcorn_palace.services.impl;

import com.att.tdp.popcorn_palace.domain.dto.ShowtimeDto;
import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;
import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;
import com.att.tdp.popcorn_palace.repositories.MovieRepository;
import com.att.tdp.popcorn_palace.repositories.ShowtimeRepository;
import com.att.tdp.popcorn_palace.services.ShowtimeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ShowtimeServiceImpl implements ShowtimeService {
    private ShowtimeRepository showtimeRepository;
    private MovieRepository movieRepository;

    public ShowtimeServiceImpl(ShowtimeRepository showtimeRepository, MovieRepository movieRepository) {
        this.showtimeRepository = showtimeRepository;
        this.movieRepository = movieRepository;
    }

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


}
