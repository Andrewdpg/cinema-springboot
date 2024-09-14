package com.andrewpg.cinema.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

/**
 * CreateReservationRequest DTO
 * Contains the necessary data to create a new reservation.
 *
 * @version 1.0
 * @since 1.0
 */
@Data
public class ReservationRequest {

    @NotNull(message = "Seat ID cannot be null")
    private UUID seatId;

}
