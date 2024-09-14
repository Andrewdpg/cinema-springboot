package com.andrewpg.cinema.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * TicketResponse DTO
 * Contains the created ticket details and associated reservations.
 *
 * @version 1.0
 * @since 1.0
 */
@Data
@Builder
public class TicketResponse {

    private UUID ticketId;
    private UUID scheduleId;
    private String customerEmail;
    private List<ReservationResponse> reservations;

}
