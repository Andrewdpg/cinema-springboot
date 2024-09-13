package com.andrewpg.cinema.repository;

import com.andrewpg.cinema.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * TicketRepository interface
 * Extends the JpaRepository interface for managing Ticket entities.
 *
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

}
