package com.andrewpg.cinema.repository;

import com.andrewpg.cinema.model.Seat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
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

    @Query("SELECT s, CASE WHEN COUNT(r) > 0 THEN 'reserved' ELSE 'available' END AS status " +
        "FROM Seat s " +
        "LEFT JOIN Reservation r ON r.seat.seatId = s.seatId AND r.ticket.schedule.scheduleId = :scheduleId " +
        "WHERE s.auditorium.id = (SELECT sch.auditorium.id FROM Schedule sch WHERE sch.scheduleId = :scheduleId) " +
        "GROUP BY s.seatId")
    List<Object[]> findSeatsWithStatusByScheduleId(UUID scheduleId);

}
