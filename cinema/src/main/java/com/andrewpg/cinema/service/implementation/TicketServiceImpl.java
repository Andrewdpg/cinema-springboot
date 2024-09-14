package com.andrewpg.cinema.service.implementation;

import com.andrewpg.cinema.dto.CreateTicketRequest;
import com.andrewpg.cinema.dto.DeleteTicketRequest;
import com.andrewpg.cinema.dto.OperationResponse;
import com.andrewpg.cinema.dto.ReservationRequest;
import com.andrewpg.cinema.dto.ReservationResponse;
import com.andrewpg.cinema.dto.TicketResponse;
import com.andrewpg.cinema.model.Customer;
import com.andrewpg.cinema.model.Reservation;
import com.andrewpg.cinema.model.Schedule;
import com.andrewpg.cinema.model.Seat;
import com.andrewpg.cinema.model.Ticket;
import com.andrewpg.cinema.repository.CustomerRepository;
import com.andrewpg.cinema.repository.ReservationRepository;
import com.andrewpg.cinema.repository.ScheduleRepository;
import com.andrewpg.cinema.repository.SeatRepository;
import com.andrewpg.cinema.repository.TicketRepository;
import com.andrewpg.cinema.service.interfaces.TicketService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * TicketServiceImpl class
 * Implements the TicketService interface for managing tickets and associated reservations.
 *
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final ScheduleRepository scheduleRepository;
    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;

    /**
     * Creates a new ticket with associated reservations.
     *
     * @param request DTO containing the customer, schedule, and reservations.
     * @return DTO containing the created ticket and associated reservations.
     */
    @Override
    @Transactional
    public TicketResponse createTicket(CreateTicketRequest request) {
        Customer customerOpt = customerRepository.findById(request.getCustomerEmail())
            .orElseThrow(()-> new IllegalArgumentException("Customer not found."));

        Schedule scheduleOpt = scheduleRepository.findById(request.getScheduleId())
            .orElseThrow(()-> new IllegalArgumentException("Schedule not found."));

        Ticket savedTicket = ticketRepository.save(Ticket.builder()
            .customer(customerOpt)
            .schedule(scheduleOpt)
            .build());

        List<Seat> seats = seatRepository.findAllById(request.getReservations().stream()
            .map(ReservationRequest::getSeatId)
            .collect(Collectors.toList()));

        if(seats.size() != request.getReservations().size()) {
            throw new IllegalArgumentException("One or more seats not found.");
        }

        if (!reservationRepository.findBySeatInAndTicketSchedule(seats, scheduleOpt).isEmpty()) {
            throw new IllegalArgumentException("One or more seats are already reserved for this schedule.");
        }
        List<Reservation> reservations = seats.stream()
            .map((seat) -> Reservation.builder().ticket(savedTicket).seat(seat).build())
            .toList();
        reservationRepository.saveAll(reservations);

        List<ReservationResponse> reservationResponses = reservations.stream()
            .map(reservation -> ReservationResponse.builder().reservationId(reservation.getReservationId()).seatId(reservation.getSeat().getSeatId()).build())
            .collect(Collectors.toList());

        return TicketResponse.builder()
            .ticketId(savedTicket.getTicketId())
            .scheduleId(savedTicket.getSchedule().getScheduleId())
            .customerEmail(savedTicket.getCustomer().getEmail())
            .reservations(reservationResponses).build();
    }

    @Override
    @Transactional
    public OperationResponse deleteTicket(DeleteTicketRequest request) {
        Ticket ticket = ticketRepository.findById(request.getTicketId())
            .orElseThrow(()-> new IllegalArgumentException("Ticket not found."));

        ticketRepository.delete(ticket);

        return OperationResponse.builder().message("Ticket deleted successfully.").build();
    }
}
