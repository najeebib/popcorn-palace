package com.att.tdp.popcorn_palace.domain.dto;

import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketBookingDto {

    private Long showtimeId;

    private int seatNumber;
    private String userId;
}
