package com.att.tdp.popcorn_palace.controllers;

import com.att.tdp.popcorn_palace.domain.dto.ShowtimeDto;
import com.att.tdp.popcorn_palace.domain.entities.ShowtimeEntity;
import com.att.tdp.popcorn_palace.domain.entities.MovieEntity;
import com.att.tdp.popcorn_palace.services.ShowtimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for ShowtimeController.
 */
@WebMvcTest(ShowtimeController.class)
public class ShowtimeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShowtimeService showtimeService;

    private ShowtimeDto sampleDto;
    private ShowtimeEntity sampleEntity;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        sampleDto = ShowtimeDto.builder()
                .movieId(1L)
                .theater("Theater 1")
                .price(10.0)
                .startTime(OffsetDateTime.now())
                .endTime(OffsetDateTime.now().plusHours(2))
                .build();

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(1L);

        sampleEntity = new ShowtimeEntity();
        sampleEntity.setId(1L);
        sampleEntity.setMovie(movieEntity);
        sampleEntity.setTheater("Theater 1");
        sampleEntity.setPrice(10.0);
        sampleEntity.setStartTime(sampleDto.getStartTime());
        sampleEntity.setEndTime(sampleDto.getEndTime());
    }

    /**
     * Tests the successful creation of a showtime.
     *
     * @throws Exception if an error occurs during the request
     */
    @Test
    void createShowtime_Success() throws Exception {
        Mockito.when(showtimeService.createShowtime(any(ShowtimeDto.class))).thenReturn(sampleDto);

        mockMvc.perform(post("/showtimes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"movieId\":1,\"theater\":\"Theater 1\",\"price\":10.5,\"startTime\":\"" + sampleDto.getStartTime() + "\",\"endTime\":\"" + sampleDto.getEndTime() + "\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.theater").value("Theater 1"));
    }

    /**
     * Tests retrieving a showtime by its ID when it exists.
     *
     * @throws Exception if an error occurs during the request
     */
    @Test
    void getShowtimeById_Success() throws Exception {
        Mockito.when(showtimeService.getShowtimeById(1L)).thenReturn(Optional.of(sampleEntity));

        mockMvc.perform(get("/showtimes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.theater").value("Theater 1"));
    }

    /**
     * Tests retrieving a showtime by its ID when it does not exist.
     *
     * @throws Exception if an error occurs during the request
     */
    @Test
    void getShowtimeById_NotFound() throws Exception {
        Mockito.when(showtimeService.getShowtimeById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/showtimes/1"))
                .andExpect(status().isNotFound());
    }

    /**
     * Tests the successful full update of a showtime.
     *
     * @throws Exception if an error occurs during the request
     */
    @Test
    void fullUpdateShowtime_Success() throws Exception {
        mockMvc.perform(put("/showtimes/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"movieId\":1,\"theater\":\"Theater 1\",\"price\":10.5,\"startTime\":\"" + sampleDto.getStartTime() + "\",\"endTime\":\"" + sampleDto.getEndTime() + "\"}"))
                .andExpect(status().isOk());

        Mockito.verify(showtimeService).fullUpdateShowtime(eq(1L), any(ShowtimeDto.class));
    }

    /**
     * Tests the successful deletion of a showtime.
     *
     * @throws Exception if an error occurs during the request
     */
    @Test
    void deleteShowtime_Success() throws Exception {
        mockMvc.perform(delete("/showtimes/1"))
                .andExpect(status().isOk());

        Mockito.verify(showtimeService).deleteShowtime(1L);
    }
}