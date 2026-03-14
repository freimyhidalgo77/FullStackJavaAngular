import { Component, inject, OnInit, ChangeDetectionStrategy, signal, computed } from '@angular/core';
import { ReservationService } from '../../services/reservation.service';
import { Reservation } from '../../models/reservation.model';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ReservationListComponent implements OnInit {
    
  private readonly reservationService = inject(ReservationService);

  readonly reservations = signal<Reservation[]>([]);
  readonly isLoading = signal<boolean>(true);
  readonly error = signal<string | null>(null);

  readonly hasReservations = computed(() => this.reservations().length > 0);

  constructor() { 
    this.loadReservations();
  }

  ngOnInit(): void {
    this.loadReservations();
  }

  loadReservations(): void {
    this.isLoading.set(true);
    this.error.set(null);

    this.reservationService.getReservations().subscribe({
      next: (reservations: Reservation[]) => {
        this.reservations.set(reservations);
        this.isLoading.set(false);
      },
      error: (err: Error) => {
        this.error.set('Error al cargar. Por favor intenta de nuevo.');
        this.isLoading.set(false);
        console.error('Error al cargar las reservas:', err);
      }
    });
  }

  cancelReservation(reservation: Reservation): void {
    if (!reservation || !reservation.id) {
      console.error('Reserva invalidada');
      return;
    }

    this.reservationService.cancelReservation(reservation.id).subscribe({
      next: () => {
        this.loadReservations();
      },
      error: (err: Error) => {
        this.error.set('Error al cancelar la reserva. Por favor intentar de nuevo.');
        console.error('Error al cancelar la reserva:', err);
      }
    });
  }
}