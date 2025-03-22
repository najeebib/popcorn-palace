package com.att.tdp.popcorn_palace.controllers;

import com.att.tdp.popcorn_palace.domain.dto.TicketBookingDto;
import com.att.tdp.popcorn_palace.domain.entities.TicketBookingEntity;
import com.att.tdp.popcorn_palace.services.TicketBookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
/**
 * REST controller for managing ticket bookings.
 */
@RestController
public class TicketBookingController {
    private TicketBookingService ticketBookingService;

    /**
     * Constructor for TicketBookingController.
     *
     * @param ticketBookingService the service to manage ticket bookings
     */
    public TicketBookingController(TicketBookingService ticketBookingService) {
        this.ticketBookingService = ticketBookingService;
    }
    /**
     * Creates a new ticket booking.
     *
     * @param ticketBooking the ticket booking to create
     * @return the ResponseEntity with status 201 (Created) and a success message, or with status 400 (Bad Request) if there is an error
     */
    @PostMapping(path = "/bookings")
    public ResponseEntity<String> createTicketBooking(@RequestBody TicketBookingDto ticketBooking) {
        try {
            TicketBookingEntity saved = ticketBookingService.createTicketBooking(ticketBooking);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Ticket booked successfully");

        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(ex.getReason());
        }
    }
}
