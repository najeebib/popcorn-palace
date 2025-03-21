package com.att.tdp.popcorn_palace.controllers;

import com.att.tdp.popcorn_palace.domain.dto.MovieDto;
import com.att.tdp.popcorn_palace.services.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    private MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(path = "/movies")
    public MovieDto createMovie(@RequestBody MovieDto movie) {

        return  this.movieService.createMovie(movie);

    }

    @GetMapping(path = "/movies/all")
    public List<MovieDto> getAllMovies() {
        return this.movieService.getAllMovies();
    }

}
