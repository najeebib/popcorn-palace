package com.att.tdp.popcorn_palace.services;

import com.att.tdp.popcorn_palace.domain.dto.MovieDto;
import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;

import java.util.List;

public interface MovieService {
    MovieDto createMovie(MovieDto movieEntity);

    List<MovieDto> getAllMovies();
    Boolean isExists(Long id);
    void fullUpdateMovie(String title, MovieDto movie);
}
