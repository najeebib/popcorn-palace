package com.att.tdp.popcorn_palace.services;

import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;

public interface MovieService {
    MovieEntity createMovie(MovieEntity movieEntity);
}
