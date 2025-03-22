package com.att.tdp.popcorn_palace.repositories;

import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;
import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for ShowtimeRepository.
 */
@DataJpaTest
class ShowtimeRepositoryTest {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private MovieRepository movieRepository;

    /**
     * Tests finding showtimes by movie ID.
     */
    @Test
    @DisplayName("Should find showtimes by movie")
    void testFindByMovieId() {
        MovieEntity movie = MovieEntity.builder()
                .title("Avatar")
                .genre("Fantasy")
                .rating(9.1)
                .duration(162)
                .releaseYear(2009)
                .build();
        movieRepository.save(movie);

        ShowtimeEntity showtime = ShowtimeEntity.builder()
                .movie(movie)
                .theater("Theater 1")
                .price(15.0)
                .startTime(OffsetDateTime.now())
                .endTime(OffsetDateTime.now().plusHours(2))
                .build();
        showtimeRepository.save(showtime);

        List<ShowtimeEntity> showtimes = showtimeRepository.findByMovieId(movie.getId());
        assertThat(showtimes).hasSize(1);
        assertThat(showtimes.getFirst().getTheater()).isEqualTo("Theater 1");
    }

    /**
     * Tests deleting showtimes by movie ID.
     */
    @Test
    @DisplayName("Should delete showtimes by movieId")
    void testDeleteByMovieId() {
        MovieEntity movie = MovieEntity.builder()
                .title("The Matrix")
                .genre("Action")
                .rating(9.1)
                .duration(136)
                .releaseYear(1999)
                .build();
        movieRepository.save(movie);

        ShowtimeEntity showtime = ShowtimeEntity.builder()
                .movie(movie)
                .theater("Theater 2")
                .price(12.0)
                .startTime(OffsetDateTime.now())
                .endTime(OffsetDateTime.now().plusHours(2))
                .build();
        showtimeRepository.save(showtime);

        showtimeRepository.deleteByMovieId(movie.getId());

        List<ShowtimeEntity> showtimes = showtimeRepository.findByMovieId(movie.getId());
        assertThat(showtimes).isEmpty();
    }
}