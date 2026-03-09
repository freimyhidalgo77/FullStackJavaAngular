package com.freimyhidalgo.reservation_backend.exception;

/**
 * Exception thrown when a reservation already exists for the specified date and time.
 */
public class ReservationAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new ReservationAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message
     */
    public ReservationAlreadyExistsException(String message) {
        super(message);
    }
}