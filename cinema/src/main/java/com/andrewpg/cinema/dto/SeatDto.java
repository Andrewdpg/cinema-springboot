package com.andrewpg.cinema.dto;

import lombok.Data;

import java.util.UUID;

/**
 * SeatDto
 * DTO for transferring seat details.
 */
@Data
public class SeatDto {
    private UUID seatId;
    private String row;
    private int column;
    private String status;
}
