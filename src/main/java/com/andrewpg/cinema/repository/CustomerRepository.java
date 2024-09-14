package com.andrewpg.cinema.repository;

import com.andrewpg.cinema.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CustomerRepository interface
 * Extends the JpaRepository interface for managing Customer entities.
 *
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
