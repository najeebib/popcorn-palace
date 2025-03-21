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

import java.util.Optional;

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
    @Override
    public Optional<ShowtimeEntity> getShowtimeById(Long id) {
        return  showtimeRepository.findById(id);
    }

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


}
