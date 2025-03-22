package com.att.tdp.popcorn_palace.repositories;

import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing MovieEntity instances.
 */
@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Long> {
    /**
     * Finds a movie by its title.
     *
     * @param title the title of the movie to find
     * @return the MovieEntity with the specified title
     */
    MovieEntity findByTitle(String title);

    /**
     * Deletes a movie by its title.
     *
     * @param title the title of the movie to delete
     */
    void deleteByTitle(String title);
}
