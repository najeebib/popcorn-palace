package com.att.tdp.popcorn_palace.services.impl;

import com.att.tdp.popcorn_palace.domain.dto.MovieDto;
import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;
import com.att.tdp.popcorn_palace.repositories.MovieRepository;
import com.att.tdp.popcorn_palace.services.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        MovieEntity movieEntity = MovieEntity.builder()
                .title(movieDto.getTitle())
                .genre(movieDto.getGenre())
                .duration(movieDto.getDuration())
                .rating(movieDto.getRating())
                .releaseYear(movieDto.getReleaseYear())
                .build();
        movieRepository.save(movieEntity);
        return  movieDto;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<MovieEntity> entities = StreamSupport.stream(movieRepository.findAll().spliterator(), false).toList();

        return entities.stream()
                .map(entity -> MovieDto.builder()
                        .title(entity.getTitle())
                        .genre(entity.getGenre())
                        .rating(entity.getRating())
                        .duration(entity.getDuration())
                        .releaseYear(entity.getReleaseYear())
                        .build())
                .toList();

    }
}
