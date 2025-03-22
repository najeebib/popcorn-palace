package com.att.tdp.popcorn_palace.controllers;

import com.att.tdp.popcorn_palace.domain.dto.TicketBookingDto;
import com.att.tdp.popcorn_palace.domain.entities.TicketBookingEntity;
import com.att.tdp.popcorn_palace.services.TicketBookingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for TicketBookingController.
 */
@WebMvcTest(TicketBookingController.class)
public class TicketBookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketBookingService ticketBookingService;

    /**
     * Tests the successful creation of a ticket booking.
     *
     * @throws Exception if an error occurs during the request
     */
    @Test
    void createTicketBooking_Success() throws Exception {
        TicketBookingEntity bookingEntity = new TicketBookingEntity();
        Mockito.when(ticketBookingService.createTicketBooking(any(TicketBookingDto.class)))
                .thenReturn(bookingEntity);

        mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"showtimeId\":1,\"userId\":2,\"seats\":3}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Ticket booked successfully"));
    }
}