package com.att.tdp.popcorn_palace.controllers;

import com.att.tdp.popcorn_palace.domain.dto.MovieDto;
import com.att.tdp.popcorn_palace.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private MovieDto sampleMovie;

    @BeforeEach
    void setUp() {
        sampleMovie = MovieDto.builder()
                .title("Inception")
                .genre("Sci-Fi")
                .duration(148)
                .build();
    }

    @Test
    void createMovie_Success() throws Exception {
        Mockito.when(movieService.createMovie(any(MovieDto.class))).thenReturn(sampleMovie);

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Inception\",\"genre\":\"Sci-Fi\",\"duration\":148}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Inception"));
    }

    @Test
    void getAllMovies_ReturnsList() throws Exception {
        Mockito.when(movieService.getAllMovies()).thenReturn(Arrays.asList(sampleMovie));

        mockMvc.perform(get("/movies/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Inception"));
    }

    @Test
    void getAllMovies_EmptyList() throws Exception {
        Mockito.when(movieService.getAllMovies()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/movies/all"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No movies found."));
    }

    @Test
    void deleteMovie_Success() throws Exception {
        mockMvc.perform(delete("/movies/Inception"))
                .andExpect(status().isOk());

        Mockito.verify(movieService).deleteMovie("Inception");
    }

    @Test
    void fullUpdateMovie_Success() throws Exception {
        mockMvc.perform(put("/movies/update/Inception")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Inception\",\"genre\":\"Sci-Fi\",\"duration\":148}"))
                .andExpect(status().isOk());

        Mockito.verify(movieService).fullUpdateMovie(eq("Inception"), any(MovieDto.class));
    }
}
