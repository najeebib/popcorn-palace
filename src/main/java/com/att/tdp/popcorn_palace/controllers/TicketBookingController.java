package com.att.tdp.popcorn_palace.controllers;

import com.att.tdp.popcorn_palace.domain.dto.TicketBookingDto;
import com.att.tdp.popcorn_palace.domain.entities.TicketBookingEntity;
import com.att.tdp.popcorn_palace.services.TicketBookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketBookingController {
    private TicketBookingService ticketBookingService;


    public TicketBookingController(TicketBookingService ticketBookingService) {
        this.ticketBookingService = ticketBookingService;
    }

    @PostMapping(path = "/ticketBookings")
    public ResponseEntity<String> createTicketBooking(@RequestBody TicketBookingDto ticketBooking) {
        TicketBookingEntity saved = ticketBookingService.createTicketBooking(ticketBooking);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ticket booked successfully");


    }
}
