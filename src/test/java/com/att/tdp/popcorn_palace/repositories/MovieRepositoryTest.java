package com.att.tdp.popcorn_palace.repositories;

import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for MovieRepository.
 */
@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * Tests saving and finding a movie by its title.
     */
    @Test
    @DisplayName("Should save and find movie by title")
    void testFindByTitle() {
        MovieEntity movie = MovieEntity.builder()
                .title("Inception")
                .genre("Sci-Fi")
                .rating(9.1)
                .duration(148)
                .releaseYear(2010)
                .build();

        movieRepository.save(movie);

        MovieEntity found = movieRepository.findByTitle("Inception");
        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("Inception");
        assertThat(found.getGenre()).isEqualTo("Sci-Fi");
    }

    /**
     * Tests deleting a movie by its title.
     */
    @Test
    @DisplayName("Should delete movie by title")
    void testDeleteByTitle() {
        MovieEntity movie = MovieEntity.builder()
                .title("Titanic")
                .genre("Drama")
                .rating(9.1)
                .duration(195)
                .releaseYear(1997)
                .build();

        movieRepository.save(movie);

        movieRepository.deleteByTitle("Titanic");

        MovieEntity found = movieRepository.findByTitle("Titanic");
        assertThat(found).isNull();
    }
}