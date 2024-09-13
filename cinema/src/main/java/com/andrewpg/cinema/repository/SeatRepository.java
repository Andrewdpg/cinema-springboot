package com.andrewpg.cinema.repository;

import com.andrewpg.cinema.model.Seat;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * SeatRepository interface
 * Extends the JpaRepository interface for managing Seat entities.
 *
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, UUID> {

}
