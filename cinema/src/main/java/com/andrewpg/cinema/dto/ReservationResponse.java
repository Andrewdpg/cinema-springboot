package com.andrewpg.cinema.dto;

import lombok.Data;

import java.util.UUID;

/**
 * ReservationResponse DTO
 * Contains the created reservation details.
 *
 * @version 1.0
 * @since 1.0
 */
@Data
public class ReservationResponse {

    private UUID reservationId;
    private UUID seatId;

    public ReservationResponse(UUID reservationId, UUID seatId) {
        this.reservationId = reservationId;
        this.seatId = seatId;
    }
}
