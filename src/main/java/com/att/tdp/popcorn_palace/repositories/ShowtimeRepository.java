package com.att.tdp.popcorn_palace.repositories;

import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
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

    @Query("SELECT s FROM ShowtimeEntity s WHERE s.theater = :theater " +
            "AND s.startTime < :endTime AND s.endTime > :startTime")
    List<ShowtimeEntity> findOverlappingShowtimes(String theater, OffsetDateTime startTime, OffsetDateTime endTime);
}
