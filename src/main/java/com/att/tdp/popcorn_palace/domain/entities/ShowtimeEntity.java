package com.att.tdp.popcorn_palace.domain.entities;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "showtime_id_generator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    private String theater;
    private Integer price;
    private Long startTime;
    private Long endTime;
}
