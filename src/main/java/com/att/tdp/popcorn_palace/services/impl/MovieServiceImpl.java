package com.att.tdp.popcorn_palace.services.impl;

import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;
import com.att.tdp.popcorn_palace.repositories.MovieRepository;
import com.att.tdp.popcorn_palace.services.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository moiveRepository) {
        this.movieRepository = moiveRepository;
    }

    @Override
    public MovieEntity createMovie(MovieEntity movieEntity) {
        return movieRepository.save(movieEntity);
    }
}
