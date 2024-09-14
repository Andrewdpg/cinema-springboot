package com.andrewpg.cinema.service.interfaces;

import com.andrewpg.cinema.dto.CreateTicketRequest;
import com.andrewpg.cinema.dto.DeleteTicketRequest;
import com.andrewpg.cinema.dto.OperationResponse;
import com.andrewpg.cinema.dto.TicketResponse;

/**
 * TicketService interface
 * Provides methods for creating and deleting tickets.
 *
 * @version 1.0
 * @since 1.0
 */
public interface TicketService {

    /**
     * Create a ticket with the given request.
     *
     * @param request the request to create a ticket
     * @return the created ticket details
     */
    TicketResponse createTicket(CreateTicketRequest request);

    /**
     * Delete a ticket with the given request.
     *
     * @param request the request to delete a ticket
     * @return the response of the operation
     */
    OperationResponse deleteTicket(DeleteTicketRequest request);
}
