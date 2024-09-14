package com.andrewpg.cinema.repository;

import com.andrewpg.cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * MovieRepository interface
 * Provides CRUD operations for the Movie entity.
 *
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
}
