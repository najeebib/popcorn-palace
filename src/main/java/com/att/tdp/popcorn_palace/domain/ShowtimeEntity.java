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
@Table(name = "showtime")
public class ShowtimeEntity {
    @Id
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private MovieEntity movieEntity;

    private String theater;
    private Integer price;
    private Long startTime;
    private Long endTime;
}
