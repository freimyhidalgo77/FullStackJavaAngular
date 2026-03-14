import { Routes } from '@angular/router';
import { ReservationListComponent } from './pages/reservation-list/reservation-list.component';
import { ReservationFormComponent } from './pages/reservation-form/reservation-form.component';


export const routes: Routes = [
  {
    path: '',
    component: ReservationListComponent
  },
  {
    path: 'create',
    component: ReservationFormComponent
  }
];
