package com.andrewpg.cinema.service.implementation;

import com.andrewpg.cinema.dto.SeatDto;
import com.andrewpg.cinema.model.Seat;
import com.andrewpg.cinema.repository.SeatRepository;
import com.andrewpg.cinema.service.interfaces.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * SeatServiceImpl class
 * Provides methods for retrieving seat information.
 * Implements SeatService interface.
 *
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    /**
     * Retrieves all available seats for a given showtime.
     *
     * @param scheduleId the schedule id to retrieve available seats for
     * @return A list of SeatDto objects.
     */
    @Override
    public List<SeatDto> getAvailableSeatsBySchedule(UUID scheduleId) {
        return null;
    }
}
