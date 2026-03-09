package com.freimyhidalgo.reservation_backend.exception;

/**
 * Exception thrown when a reservation is not found.
 */
public class ReservationNotFoundException extends RuntimeException {

    /**
     * Constructs a new ReservationNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public ReservationNotFoundException(String message) {
        super(message);
    }
}