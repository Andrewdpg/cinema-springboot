package com.andrewpg.cinema.repository;

import com.andrewpg.cinema.model.Reservation;
import com.andrewpg.cinema.model.Schedule;
import com.andrewpg.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * ReservationRepository interface
 * Extends the JpaRepository interface for managing Reservation entities.
 *
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    Collection<Object> findBySeatInAndTicketSchedule(List<Seat> seats, Schedule scheduleOpt);
}
