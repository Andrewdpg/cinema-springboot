package com.andrewpg.cinema.service;

import com.andrewpg.cinema.dto.CreateTicketRequest;
import com.andrewpg.cinema.dto.DeleteTicketRequest;
import com.andrewpg.cinema.dto.OperationResponse;
import com.andrewpg.cinema.dto.ReservationRequest;
import com.andrewpg.cinema.dto.TicketResponse;
import com.andrewpg.cinema.model.*;
import com.andrewpg.cinema.repository.*;
import com.andrewpg.cinema.service.implementation.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test the createTicket method of the TicketService class.
     * The test case is successful when the method returns a TicketResponse object with the correct values.
     */
    @Test
    public void testCreateTicket_Success() {
        UUID scheduleId = UUID.randomUUID();
        String customerEmail = "john.doe@example.com";
        UUID seatId1 = UUID.randomUUID();
        UUID seatId2 = UUID.randomUUID();

        ReservationRequest reservationRequest1 = new ReservationRequest();
        reservationRequest1.setSeatId(seatId1);

        ReservationRequest reservationRequest2 = new ReservationRequest();
        reservationRequest2.setSeatId(seatId2);

        CreateTicketRequest request = new CreateTicketRequest();
        request.setScheduleId(scheduleId);
        request.setCustomerEmail(customerEmail);
        request.setReservations(List.of(reservationRequest1, reservationRequest2));

        Customer customer = new Customer();
        customer.setEmail(customerEmail);

        Schedule schedule = new Schedule();
        schedule.setScheduleId(scheduleId);

        Seat seat1 = new Seat();
        seat1.setSeatId(seatId1);

        Seat seat2 = new Seat();
        seat2.setSeatId(seatId2);

        Ticket ticket = new Ticket();
        ticket.setTicketId(UUID.randomUUID());
        ticket.setCustomer(customer);
        ticket.setSchedule(schedule);

        when(customerRepository.findById(customerEmail)).thenReturn(Optional.of(customer));
        when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(schedule));
        when(seatRepository.findAllById(any())).thenReturn(List.of(seat1, seat2));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);
        when(reservationRepository.findBySeatInAndTicketSchedule(any(), any())).thenReturn(List.of());

        TicketResponse response = ticketService.createTicket(request);

        assertNotNull(response);
        assertEquals(customerEmail, response.getCustomerEmail());
        assertEquals(scheduleId, response.getScheduleId());
        assertEquals(2, response.getReservations().size());
    }

    /**
     * Test the createTicket method of the TicketService class.
     * The test case is successful when the method throws an IllegalArgumentException with the message "Customer not found."
     */
    @Test
    public void testCreateTicket_CustomerNotFound() {
        CreateTicketRequest request = new CreateTicketRequest();
        request.setCustomerEmail("jonh.doe@example.com");
        request.setScheduleId(UUID.randomUUID());
        request.setReservations(List.of());

        when(customerRepository.findById(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> ticketService.createTicket(request));

        assertEquals("Customer not found.", exception.getMessage());
    }

    /**
     * Test the createTicket method of the TicketService class.
     * The test case is successful when the method throws an IllegalArgumentException with the message "Seat not found."
     */
    @Test
    public void testCreateTicket_ScheduleNotFound() {
        UUID scheduleId = UUID.randomUUID();
        CreateTicketRequest request = new CreateTicketRequest();
        request.setCustomerEmail("jonh.doe@example.com");
        request.setScheduleId(UUID.randomUUID());
        request.setReservations(List.of());

        Customer customer = new Customer();
        customer.setEmail("john.doe@example.com");

        when(customerRepository.findById(anyString())).thenReturn(Optional.of(customer));
        when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> ticketService.createTicket(request));

        assertEquals("Schedule not found.", exception.getMessage());
    }

    /**
     * Test the createTicket method of the TicketService class.
     * The test case is successful when the method throws an IllegalArgumentException with the message "Seat not found."
     */
    @Test
    public void testDeleteTicket_Success() {
        UUID ticketId = UUID.randomUUID();
        DeleteTicketRequest request = new DeleteTicketRequest();
        request.setTicketId(ticketId);

        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketId);

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        OperationResponse response = ticketService.deleteTicket(request);

        assertNotNull(response);
        assertEquals("Ticket deleted successfully.", response.getMessage());
        verify(ticketRepository, times(1)).delete(ticket);
    }

    /**
     * Test the deleteTicket method of the TicketService class.
     * The test case is successful when the method throws an IllegalArgumentException with the message "Ticket not found."
     */
    @Test
    public void testDeleteTicket_TicketNotFound() {
        UUID ticketId = UUID.randomUUID();
        DeleteTicketRequest request = new DeleteTicketRequest();
        request.setTicketId(ticketId);

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> ticketService.deleteTicket(request));

        assertEquals("Ticket not found.", exception.getMessage());
    }
}