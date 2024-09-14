package com.andrewpg.cinema.service;

import com.andrewpg.cinema.dto.ScheduleDto;
import com.andrewpg.cinema.model.Movie;
import com.andrewpg.cinema.model.Schedule;
import com.andrewpg.cinema.repository.ScheduleRepository;
import com.andrewpg.cinema.service.implementation.ScheduleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test the getSchedulesByMovieId method of the ScheduleService class.
     * The test case is successful when the method returns a list of ScheduleDto objects with the correct values.
     */
    @Test
    public void testGetSchedulesByMovieId_Success() {
        // Arrange
        UUID movieId = UUID.randomUUID();
        Movie movie = new Movie();
        movie.setMovieId(movieId);
        movie.setTitle("Test Movie");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");

        Date date, time1, time2;
        try {
            date = dateFormat.parse("2024-09-14");
            time1 = timeFormat.parse("10:00");
            time2 = timeFormat.parse("12:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Schedule schedule1 = new Schedule();
        schedule1.setScheduleId(UUID.randomUUID());
        schedule1.setMovie(movie);
        schedule1.setTime(time1);
        schedule1.setDate(date);
        schedule1.setPrice(10.0);

        Schedule schedule2 = new Schedule();
        schedule2.setScheduleId(UUID.randomUUID());
        schedule2.setMovie(movie);
        schedule2.setTime(time2);
        schedule2.setDate(date);
        schedule2.setPrice(12.0);

        when(scheduleRepository.findByMovie_MovieId(movieId)).thenReturn(List.of(schedule1, schedule2));

        List<ScheduleDto> schedules = scheduleService.getSchedulesByMovieId(movieId);

        assertNotNull(schedules);
        assertEquals(2, schedules.size());
        assertEquals("Test Movie", schedules.get(0).getMovieTitle());
        assertTrue(schedules.get(0).getTime().toString().contains("10:00"));
    }
}