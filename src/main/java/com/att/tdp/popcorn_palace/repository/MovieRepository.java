package com.att.tdp.popcorn_palace.repository;

import com.att.tdp.popcorn_palace.domain.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
}
