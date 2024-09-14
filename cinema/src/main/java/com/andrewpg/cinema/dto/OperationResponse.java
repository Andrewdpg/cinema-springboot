package com.andrewpg.cinema.dto;

import lombok.Builder;
import lombok.Data;

/**
 * OperationResponse DTO
 * Contains the message and data of the operation response.
 *
 * @version 1.0
 * @since 1.0
 */
@Data
@Builder
public class OperationResponse {
    private String message;
    private Object data;
}
