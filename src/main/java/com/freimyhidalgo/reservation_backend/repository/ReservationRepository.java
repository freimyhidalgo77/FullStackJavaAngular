package com.freimyhidalgo.reservation_backend.repository;

import com.freimyhidalgo.reservation_backend.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Repository interface for Reservation entity operations.
 */

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Checks if a reservation already exists for the specified date and time.
     *
     * @param date the reservation date
     * @param time the reservation time
     * @return true if a reservation exists, false otherwise
     */
    boolean existsByDateAndTime(LocalDate date, LocalTime time);
}