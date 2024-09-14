package com.andrewpg.cinema.controller;

import com.andrewpg.cinema.dto.ScheduleDto;
import com.andrewpg.cinema.service.interfaces.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * ScheduleController
 * Handles REST API requests for movie schedules (schedules).
 */
@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * Retrieves all schedules for a given movie.
     *
     * @param movieId The movie ID.
     * @return A ResponseEntity containing a list of ScheduleDto objects.
     */
    @GetMapping
    public ResponseEntity<List<ScheduleDto>> getSchedules(@RequestParam UUID movieId) {
        List<ScheduleDto> schedules = scheduleService.getSchedulesByMovieId(movieId);
        return ResponseEntity.ok(schedules);
    }
}
