package com.andrewpg.cinema.service.interfaces;

import com.andrewpg.cinema.dto.SeatDto;

import java.util.List;
import java.util.UUID;

/**
 * SeatService interface
 * Provides methods for retrieving seat information.
 *
 * @version 1.0
 * @since 1.0
 */
public interface SeatService {

    /**
     * Retrieves all available seats for a given showtime.
     *
     * @param scheduleId the schedule id to retrieve available seats for
     * @return A list of SeatDto objects.
     */
    List<SeatDto> getAvailableSeatsBySchedule(UUID scheduleId);
}
