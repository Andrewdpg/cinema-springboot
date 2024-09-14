package com.andrewpg.cinema.controller;

import com.andrewpg.cinema.dto.SeatDto;
import com.andrewpg.cinema.service.interfaces.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * SeatController
 * Handles REST API requests for available seats.
 */
@RestController
@RequestMapping("/api/v1/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    /**
     * Retrieves all available seats for a given showtime.
     *
     * @param scheduleId The schedule ID to retrieve available seats for.
     * @return A ResponseEntity containing a list of SeatDto objects.
     */
    @GetMapping
    public ResponseEntity<List<SeatDto>> getAvailableSeats(@RequestParam UUID scheduleId) {
        List<SeatDto> seats = seatService.getSeatsBySchedule(scheduleId);
        return ResponseEntity.ok(seats);
    }
}
