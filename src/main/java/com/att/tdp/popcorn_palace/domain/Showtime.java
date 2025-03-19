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
public class Showtime {
    private Long id;

    @ManyToOne
    private Movie movie;

    private String theater;
    private Integer price;
    private Long startTime;
    private Long endTime;
}
