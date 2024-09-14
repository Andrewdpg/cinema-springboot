package com.andrewpg.cinema.controller;

import com.andrewpg.cinema.dto.CreateTicketRequest;
import com.andrewpg.cinema.dto.DeleteTicketRequest;
import com.andrewpg.cinema.dto.OperationResponse;
import com.andrewpg.cinema.dto.TicketResponse;
import com.andrewpg.cinema.service.interfaces.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



/**
 * TicketController class
 * Handles REST API requests related to ticket creation and reservation associations.
 *
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    /**
     * Creates a new ticket with associated reservations.
     *
     * @param request DTO containing the customer, schedule, and reservations.
     * @return DTO containing the created ticket and associated reservations.
     */
    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@Valid @RequestBody CreateTicketRequest request) {
        TicketResponse ticketResponse = ticketService.createTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketResponse);
    }

    /**
     * Deletes a ticket with the given request.
     *
     * @param request DTO containing the ticket ID to delete.
     * @return DTO containing the response of the operation.
     */
    @DeleteMapping
    public ResponseEntity<OperationResponse> deleteTicket(@Valid @RequestBody DeleteTicketRequest request) {
        OperationResponse ticketResponse = ticketService.deleteTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketResponse);
    }

    /**
     * Handles exceptions thrown by the controller.
     *
     * @param e the exception thrown
     * @return the response entity with the exception status
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<OperationResponse> handleArgument(Exception e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(OperationResponse.builder().message(e.getMessage()).build());
    }

    /**
     * Handles exceptions thrown by the controller.
     *
     * @param e the exception thrown
     * @return the response entity with the exception status
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<OperationResponse> handleException(Exception e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}
