package com.andrewpg.cinema.service.interfaces;

import com.andrewpg.cinema.dto.MovieDto;

import java.util.List;

/**
 * MovieService interface
 * Provides methods for retrieving movie information.
 *
 * @version 1.0
 * @since 1.0
 */
public interface MovieService {

    /**
     * Retrieves all movies.
     *
     * @return A list of MovieDto objects.
     */
    List<MovieDto> getAllMovies();
}
