package com.att.tdp.popcorn_palace.repository;

import com.att.tdp.popcorn_palace.domain.Showtime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeRepository extends CrudRepository<Showtime, Long> {
}
