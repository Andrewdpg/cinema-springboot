package com.andrewpg.cinema.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * CreateTicketRequest DTO
 * Contains the necessary data to create a new ticket with associated reservations.
 *
 * @version 1.0
 * @since 1.0
 */
@Data
public class CreateTicketRequest {

    @NotNull(message = "Schedule ID cannot be null")
    private UUID scheduleId;

    @Email(message = "Customer email must be a valid email address")
    @NotNull(message = "Customer email cannot be null")
    private String customerEmail;

    @NotEmpty(message = "Reservations list cannot be empty")
    private List<ReservationRequest> reservations;
}
