package com.freimyhidalgo.reservation_backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

import com.freimyhidalgo.reservation_backend.model.ReservationStatus;

/**
 * JPA entity representing a reservation in the system.
 */
@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private LocalDate date;

    private LocalTime time;

    private String service;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;


}
