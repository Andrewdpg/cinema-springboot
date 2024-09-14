package com.andrewpg.cinema.dto;

import lombok.Data;

import java.util.UUID;

/**
 * DeleteTicketRequest DTO
 * Contains the ticketId to be deleted.
 *
 * @version 1.0
 * @since 1.0
 */
@Data
public class DeleteTicketRequest {
    private UUID ticketId;
}
