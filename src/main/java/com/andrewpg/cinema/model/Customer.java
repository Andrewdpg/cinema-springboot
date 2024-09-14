package com.andrewpg.cinema.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer entity
 * This class represents a customer entity in the database.
 * A customer has an email, fullname, and phone number.
 *
 * @version 1.0
 * @since 1.0
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "email", length = 511)
    private String email;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

}