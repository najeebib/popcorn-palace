package com.att.tdp.popcorn_palace.domain.dto;

import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowtimeDto {

    private MovieDto movie;

    private String theater;
    private Integer price;
    private Long startTime;
    private Long endTime;
}
