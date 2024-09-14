package com.andrewpg.cinema.repository;

import com.andrewpg.cinema.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * ScheduleRepository interface
 * Extends the JpaRepository interface for managing Schedule entities.
 *
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    List<Schedule> findByMovie_MovieId(UUID movieId);
}
