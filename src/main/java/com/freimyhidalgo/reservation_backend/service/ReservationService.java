package com.freimyhidalgo.reservation_backend.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.freimyhidalgo.reservation_backend.exception.ReservationAlreadyExistsException;
import com.freimyhidalgo.reservation_backend.exception.ReservationNotFoundException;
import com.freimyhidalgo.reservation_backend.model.ReservationStatus;
import com.freimyhidalgo.reservation_backend.model.entity.Reservation;
import com.freimyhidalgo.reservation_backend.repository.ReservationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * Service class containing the business logic for reservation operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;

    /**
     * Retrieves all reservations.
     *
     * @return a list of all reservations
     */
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    /**
     * Creates a new reservation if no reservation exists for the same date and time.
     *
     * @param reservation the reservation to create
     * @return the created reservation
     * @throws ReservationAlreadyExistsException if a reservation already exists for the date and time
     */
    @Transactional
    public Reservation createReservation(Reservation reservation) {
        Objects.requireNonNull(reservation, "reservation must not be null");
        if (reservationRepository.existsByDateAndTime(reservation.getDate(), reservation.getTime())) {
            throw new ReservationAlreadyExistsException("A reservation already exists for the specified date and time.");
        }

        reservation.setStatus(ReservationStatus.ACTIVe);
        return reservationRepository.save(reservation);
    }

    /**
     * Cancels a reservation by its ID.
     *
     * @param id the ID of the reservation to cancel
     * @throws ReservationNotFoundException if the reservation is not found
     */
    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with ID: " + id));
        reservation.setStatus(ReservationStatus.CANCELED);
        reservationRepository.save(reservation);
    }


}