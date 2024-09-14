package com.andrewpg.cinema.service.interfaces;

import com.andrewpg.cinema.dto.ScheduleDto;

import java.util.List;
import java.util.UUID;

/**
 * ScheduleService interface
 * Provides methods for retrieving schedule information.
 *
 * @version 1.0
 * @since 1.0
 */
public interface ScheduleService {

    /**
     * Retrieves all schedules.
     *
     * @return A list of ScheduleDto objects.
     */
    List<ScheduleDto> getSchedulesByMovieId(UUID movieId);

}
