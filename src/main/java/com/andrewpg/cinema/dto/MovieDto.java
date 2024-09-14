package com.andrewpg.cinema.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * MovieDto
 * DTO for transferring movie details.
 */
@Data
@Builder
public class MovieDto {
    private UUID movieId;
    private String title;
    private String director;
    private int runtime;
    private Date releaseDate;
    private String image;
}
