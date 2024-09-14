package com.andrewpg.cinema.service.implementation;

import com.andrewpg.cinema.dto.ScheduleDto;
import com.andrewpg.cinema.model.Schedule;
import com.andrewpg.cinema.repository.ScheduleRepository;
import com.andrewpg.cinema.service.interfaces.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * ScheduleServiceImpl class
 * Implements the ScheduleService interface.
 *
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    /**
     * Retrieves a list of schedules for a movie.
     *
     * @param movieId The ID of the movie.
     * @return A list of ScheduleDto objects.
     */
    @Override
    public List<ScheduleDto> getSchedulesByMovieId(UUID movieId) {
        List<Schedule> schedules = scheduleRepository.findByMovie_MovieId(movieId);
        return schedules.stream().map(schedule -> ScheduleDto.builder()
                    .scheduleId(schedule.getScheduleId())
                    .movieId(schedule.getMovie().getMovieId())
                    .time(schedule.getTime())
                    .date(schedule.getDate())
                    .movieTitle(schedule.getMovie().getTitle())
                    .build()
        ).collect(Collectors.toList());
    }
}
