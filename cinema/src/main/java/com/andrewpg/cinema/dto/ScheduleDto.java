package com.andrewpg.cinema.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * ScheduleDto
 * DTO for transferring showtime details.
 */
@Data
@Builder
public class ScheduleDto {
    private UUID scheduleId;
    private Date date;
    private Date time;
    private UUID movieId;
    private String movieTitle;
}
