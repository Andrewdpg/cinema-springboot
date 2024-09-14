package com.andrewpg.cinema.service;

import com.andrewpg.cinema.dto.SeatDto;
import com.andrewpg.cinema.model.Seat;
import com.andrewpg.cinema.repository.SeatRepository;
import com.andrewpg.cinema.service.implementation.SeatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SeatServiceTest {

    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private SeatServiceImpl seatService;

    @BeforeEach
    public void setUp() {
        seatRepository = mock(SeatRepository.class);
        seatService = new SeatServiceImpl(seatRepository);
    }

    /**
     * Test the getSeatsBySchedule method of the SeatService class.
     * The test case is successful when the method returns a list of SeatDto objects with the correct values.
     */
    @Test
    public void testGetSeatsBySchedule_Success() {
        UUID scheduleId = UUID.randomUUID();
        Seat seat1 = new Seat();
        seat1.setSeatId(UUID.randomUUID());
        seat1.setRow("A");
        seat1.setCol(1);

        Seat seat2 = new Seat();
        seat2.setSeatId(UUID.randomUUID());
        seat2.setRow("A");
        seat2.setCol(2);

        when(seatRepository.findSeatsWithStatusByScheduleId(any(UUID.class)))
            .thenReturn(List.of(
                new Object[]{seat1, "AVAILABLE"},
                new Object[]{seat2, "RESERVED"}
            ));

        List<SeatDto> seats = seatService.getSeatsBySchedule(scheduleId);

        verify(seatRepository, times(1)).findSeatsWithStatusByScheduleId(any(UUID.class));

        assertNotNull(seats);
        assertEquals(2, seats.size());
        assertEquals("AVAILABLE", seats.get(0).getStatus());
        assertEquals("RESERVED", seats.get(1).getStatus());
    }
}