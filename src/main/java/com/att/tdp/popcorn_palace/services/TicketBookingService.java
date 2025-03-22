package com.att.tdp.popcorn_palace.services;

import com.att.tdp.popcorn_palace.domain.dto.TicketBookingDto;
import com.att.tdp.popcorn_palace.domain.entities.TicketBookingEntity;
/**
 * Service interface for managing ticket bookings.
 */
public interface TicketBookingService {
    /**
     * Creates a new ticket booking.
     *
     * @param ticketBookingEntity the ticket booking data transfer object
     * @return the created ticket booking entity
     */
    TicketBookingEntity createTicketBooking(TicketBookingDto ticketBookingEntity);
}
