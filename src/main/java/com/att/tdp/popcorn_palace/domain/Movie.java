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
public class Movie {
    private Long id;

    private String title;
    private String genre;
    private String rating;
    private Integer duration;
    private Integer releaseYear;
}
