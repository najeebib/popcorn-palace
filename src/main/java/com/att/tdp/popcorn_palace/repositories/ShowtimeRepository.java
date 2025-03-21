package com.att.tdp.popcorn_palace.repositories;

import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository extends CrudRepository<ShowtimeEntity, Long> {
    void deleteByMovieId(Long movieId);

    List<ShowtimeEntity> findByMovieId(Long id);
}
