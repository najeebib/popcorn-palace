package com.att.tdp.popcorn_palace.services;

import com.att.tdp.popcorn_palace.domain.dto.TicketBookingDto;
import com.att.tdp.popcorn_palace.domain.entities.TicketBookingEntity;

public interface TicketBookingService {
    TicketBookingEntity createTicketBooking(TicketBookingDto ticketBookingEntity);
}
