package com.att.tdp.popcorn_palace.repositories;

import com.att.tdp.popcorn_palace.domain.TicketBooking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketBookingRepository extends CrudRepository<TicketBooking, Long> {
}
