package com.att.tdp.popcorn_palace.services.impl;

import com.att.tdp.popcorn_palace.domain.dto.TicketBookingDto;
import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;
import com.att.tdp.popcorn_palace.domain.entities.TicketBookingEntity;
import com.att.tdp.popcorn_palace.repositories.ShowtimeRepository;
import com.att.tdp.popcorn_palace.repositories.TicketBookingRepository;
import com.att.tdp.popcorn_palace.services.TicketBookingService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
/**
 * Service implementation for managing ticket bookings.
 */
@Service
public class TicketBookingServiceImpl implements TicketBookingService {
    private TicketBookingRepository ticketBookingRepository;
    private ShowtimeRepository showtimeRepository;
    /**
     * Constructs a new TicketBookingServiceImpl with the specified repositories.
     *
     * @param ticketBookingRepository the ticket booking repository
     * @param showtimeRepository the showtime repository
     */
    public TicketBookingServiceImpl(TicketBookingRepository ticketBookingRepository, ShowtimeRepository showtimeRepository) {
        this.ticketBookingRepository = ticketBookingRepository;
        this.showtimeRepository = showtimeRepository;
    }
    /**
     * Creates a new ticket booking.
     *
     * @param ticketBookingDto the ticket booking data transfer object
     * @return the created ticket booking entity
     * @throws ResponseStatusException if the showtime is not found or the seat is already booked
     */
    @Override
    public TicketBookingEntity createTicketBooking(TicketBookingDto ticketBookingDto) {
        Long showtimeId = ticketBookingDto.getShowtimeId();

        ShowtimeEntity showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Showtime not found"));

        boolean seatTaken = ticketBookingRepository.existsByShowtimeIdAndSeatNumber(showtimeId, ticketBookingDto.getSeatNumber());
        if (seatTaken) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Seat already booked");
        }

        TicketBookingEntity booking = TicketBookingEntity.builder()
                .seatNumber(ticketBookingDto.getSeatNumber())
                .userId(ticketBookingDto.getUserId())
                .showtime(showtime)
                .build();

        return ticketBookingRepository.save(booking);
    }


}
