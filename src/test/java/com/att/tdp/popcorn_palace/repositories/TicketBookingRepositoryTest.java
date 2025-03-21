package com.att.tdp.popcorn_palace.repositories;

import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;
import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;
import com.att.tdp.popcorn_palace.domain.entities.TicketBookingEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TicketBookingRepositoryTest {

    @Autowired
    private TicketBookingRepository ticketBookingRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    @DisplayName("Should check existence by showtime and seatNumber")
    void testExistsByShowtimeIdAndSeatNumber() {
        MovieEntity movie = MovieEntity.builder()
                .title("Interstellar")
                .genre("Sci-Fi")
                .rating("PG-13")
                .duration(169)
                .releaseYear(2014)
                .build();
        movieRepository.save(movie);

        ShowtimeEntity showtime = ShowtimeEntity.builder()
                .movie(movie)
                .theater("Theater 3")
                .price(10)
                .startTime(OffsetDateTime.now())
                .endTime(OffsetDateTime.now().plusHours(3))
                .build();
        showtimeRepository.save(showtime);

        TicketBookingEntity booking = TicketBookingEntity.builder()
                .showtime(showtime)
                .seatNumber(7)
                .userId("user123")
                .build();
        ticketBookingRepository.save(booking);

        boolean exists = ticketBookingRepository.existsByShowtimeIdAndSeatNumber(showtime.getId(), 7);
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Should find bookings by showtime")
    void testFindByShowtimeId() {
        MovieEntity movie = MovieEntity.builder()
                .title("Joker")
                .genre("Drama")
                .rating("R")
                .duration(122)
                .releaseYear(2019)
                .build();
        movieRepository.save(movie);

        ShowtimeEntity showtime = ShowtimeEntity.builder()
                .movie(movie)
                .theater("Theater 4")
                .price(9)
                .startTime(OffsetDateTime.now())
                .endTime(OffsetDateTime.now().plusHours(2))
                .build();
        showtimeRepository.save(showtime);

        TicketBookingEntity booking = TicketBookingEntity.builder()
                .showtime(showtime)
                .seatNumber(15)
                .userId("user456")
                .build();
        ticketBookingRepository.save(booking);

        List<TicketBookingEntity> bookings = ticketBookingRepository.findByShowtimeId(showtime.getId());
        assertThat(bookings).hasSize(1);
        assertThat(bookings.getFirst().getSeatNumber()).isEqualTo(15);
        assertThat(bookings.getFirst().getUserId()).isEqualTo("user456");
    }
}
