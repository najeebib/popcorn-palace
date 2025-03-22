package com.att.tdp.popcorn_palace.repositories;

import com.att.tdp.popcorn_palace.domain.entities.TicketBookingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Repository interface for managing TicketBookingEntity instances.
 */
@Repository
public interface TicketBookingRepository extends CrudRepository<TicketBookingEntity, Long> {
    /**
     * Checks if a ticket booking exists by showtime ID and seat number.
     *
     * @param showtimeId the ID of the showtime
     * @param seatNumber the seat number
     * @return true if a booking exists, false otherwise
     */
    boolean existsByShowtimeIdAndSeatNumber(Long showtimeId, int seatNumber);
    /**
     * Finds ticket bookings by showtime ID.
     *
     * @param id the ID of the showtime
     * @return a list of TicketBookingEntity instances for the specified showtime ID
     */
    List<TicketBookingEntity> findByShowtimeId(Long id);
}
