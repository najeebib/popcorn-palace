package com.att.tdp.popcorn_palace.repositories;

import com.att.tdp.popcorn_palace.domain.MovieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Long> {
}
