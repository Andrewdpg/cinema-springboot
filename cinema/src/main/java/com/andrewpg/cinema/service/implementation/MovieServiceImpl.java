package com.andrewpg.cinema.service.implementation;

import com.andrewpg.cinema.dto.MovieDto;
import com.andrewpg.cinema.model.Movie;
import com.andrewpg.cinema.repository.MovieRepository;
import com.andrewpg.cinema.service.interfaces.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * MovieServiceImpl class
 * Implements the MovieService interface for managing movie information.
 *
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    /**
     * Retrieves all movies and converts them to DTOs.
     *
     * @return A list of MovieDto objects.
     */
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(movie -> MovieDto.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .runtime(movie.getRuntime())
                .releaseDate(movie.getReleaseDate())
                .image(movie.getImage())
                .build()
        ).collect(Collectors.toList());
    }
}
