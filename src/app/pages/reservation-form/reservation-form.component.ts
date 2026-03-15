import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReservationService } from '../../services/reservation.service';
import { ToastComponent } from '../../shared/toast/toast.component';
import { Reservation, ReservationStatus } from '../../models/reservation.model';

@Component({
  selector: 'app-reservation-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, ToastComponent],
  templateUrl: './reservation-form.component.html',
  styleUrls: ['./reservation-form.component.css']
})
export class ReservationFormComponent {
  private fb = inject(FormBuilder);
  private reservationService = inject(ReservationService);

  reservationForm: FormGroup;
  services = ['Software Development', 'Infrastructure Consulting', 'Code Review', 'System Architecture'];
  showToast = signal(false);
  toastMessage = signal('');

  constructor() {
    this.reservationForm = this.fb.group({
      customerName: ['', Validators.required],
      date: ['', Validators.required],
      time: ['', Validators.required],
      service: ['', Validators.required]
    });
  }

  readonly isSubmitting = signal(false);

  onSubmit() {
    if (this.reservationForm.valid) {
      const reservation: Reservation = {
        customerName: this.reservationForm.value.customerName,
        date: this.reservationForm.value.date,
        time: `${this.reservationForm.value.time}:00`,
        service: this.reservationForm.value.service,
       status: 'ACTIVE' as ReservationStatus

      };

      this.reservationService.createReservation(reservation).subscribe({
        next: () => {
          // Success, maybe reset form or navigate
          this.reservationForm.reset();
        },
   error: (error) => {
  // El backend retorna { message: "Ya existe una reserva..." } en el body
  const errorMessage = error.error?.message || 'Error al guardar la reserva';
  this.toastMessage.set(errorMessage);
  this.showToast.set(true);
  setTimeout(() => this.showToast.set(false), 3000);
}
      });
    }
  }
}