package com.att.tdp.popcorn_palace.repositories;

import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Repository interface for managing ShowtimeEntity instances.
 */
@Repository
public interface ShowtimeRepository extends CrudRepository<ShowtimeEntity, Long> {
    /**
     * Deletes showtimes by the movie ID.
     *
     * @param movieId the ID of the movie whose showtimes are to be deleted
     */
    void deleteByMovieId(Long movieId);
    /**
     * Finds showtimes by the movie ID.
     *
     * @param id the ID of the movie whose showtimes are to be found
     * @return a list of ShowtimeEntity instances for the specified movie ID
     */
    List<ShowtimeEntity> findByMovieId(Long id);
}
