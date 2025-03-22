package com.att.tdp.popcorn_palace.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Entity class representing a ticket booking.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ticket_booking")
public class TicketBookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_booking_id_generator")
    @SequenceGenerator(name = "ticket_booking_id_generator", sequenceName = "ticket_booking_id_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "showtime_id")
    private ShowtimeEntity showtime;

    private int seatNumber;
    private String userId;
}
