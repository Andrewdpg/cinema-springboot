package com.andrewpg.cinema.service.interfaces;

import com.andrewpg.cinema.dto.CreateTicketRequest;
import com.andrewpg.cinema.dto.TicketResponse;

public interface TicketService {
    TicketResponse createTicket(CreateTicketRequest request);
}
