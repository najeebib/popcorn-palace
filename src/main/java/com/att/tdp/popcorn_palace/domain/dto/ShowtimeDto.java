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

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowtimeDto {

    private Long movieId;

    private String theater;
    private Integer price;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;

}
