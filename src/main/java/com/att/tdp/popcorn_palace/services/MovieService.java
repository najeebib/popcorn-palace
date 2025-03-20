package com.att.tdp.popcorn_palace.services;

import com.att.tdp.popcorn_palace.domain.dto.MovieDto;
import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;

public interface MovieService {
    MovieDto createMovie(MovieDto movieEntity);
}
