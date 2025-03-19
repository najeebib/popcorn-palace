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
public class TicketBooking {
    private Long id;

    @ManyToOne
    private Showtime showtime;

    private int seatNumber;
    private String customerName;
}
