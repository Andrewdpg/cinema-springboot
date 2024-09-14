package com.andrewpg.cinema.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Reservation entity
 * This class represents a reservation entity in the database.
 * A reservation has a reservation ID, a seat, and a ticket.
 *
 * @version 1.0
 * @since 1.0
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"seat_seat_id", "ticket_ticket_id"})
})
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "reservation_id")
    private UUID reservationId;

    @ManyToOne
    @JoinColumn(name = "seat_seat_id", nullable = false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "ticket_ticket_id", nullable = false)
    private Ticket ticket;

}
