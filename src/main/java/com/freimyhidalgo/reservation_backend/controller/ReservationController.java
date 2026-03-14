package com.freimyhidalgo.reservation_backend.controller;

import com.freimyhidalgo.reservation_backend.model.entity.Reservation;
import com.freimyhidalgo.reservation_backend.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing reservations.
 */
@CrossOrigin(origins = "http://localhost:4200") // allows CORS requests from Angular frontend
@RestController
@RequestMapping("/reservas")

public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * GET /reservas - Lists all reservations.
     *
     * @return a list of all reservations with HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    /**
     * POST /reservas - Creates a new reservation.
     *
     * @param reservation the reservation to create
     * @return the created reservation with HTTP 201 Created
     */
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation created = reservationService.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * DELETE /reservas/{id} - Cancels a reservation by ID.
     *
     * @param id the ID of the reservation to cancel
     * @return HTTP 204 No Content if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
}