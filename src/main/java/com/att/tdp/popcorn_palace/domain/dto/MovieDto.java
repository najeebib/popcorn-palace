package com.att.tdp.popcorn_palace.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {
    private String title;

    private String genre;

    private Double rating;

    private Integer duration;

    private Integer releaseYear;
}
