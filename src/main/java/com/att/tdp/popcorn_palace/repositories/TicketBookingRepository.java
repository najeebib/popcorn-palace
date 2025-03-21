package com.att.tdp.popcorn_palace.repositories;

import com.att.tdp.popcorn_palace.domain.entities.TicketBookingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketBookingRepository extends CrudRepository<TicketBookingEntity, Long> {
    boolean existsByShowtimeIdAndSeatNumber(Long showtimeId, int seatNumber);

    List<TicketBookingEntity> findByShowtimeId(Long id);
}
