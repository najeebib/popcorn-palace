package com.att.tdp.popcorn_palace.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ticket_booking")
public class TicketBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_booking_id_generator")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;

    private int seatNumber;
    private String customerName;
}
