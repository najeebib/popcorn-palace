package com.att.tdp.popcorn_palace.controllers;

import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;
import com.att.tdp.popcorn_palace.domain.dto.MovieDto;
import com.att.tdp.popcorn_palace.mappers.Mapper;
import com.att.tdp.popcorn_palace.services.MovieService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    private MovieService movieService;

    private Mapper<MovieEntity, MovieDto> movieMapper;

    public MovieController(MovieService movieService, Mapper<MovieEntity, MovieDto> movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping(path = "/movies")
    public MovieDto createMovie(@RequestBody MovieDto movie) {
        MovieEntity movieEntity = movieMapper.mapFrom(movie);
        MovieEntity savedMovieEntity =  this.movieService.createMovie(movieEntity);
        return movieMapper.mapTo(savedMovieEntity);
    }

}
