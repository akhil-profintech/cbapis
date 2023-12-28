import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { CREObservationsComponent } from './list/cre-observations.component';
import { CREObservationsDetailComponent } from './detail/cre-observations-detail.component';
import { CREObservationsUpdateComponent } from './update/cre-observations-update.component';
import CREObservationsResolve from './route/cre-observations-routing-resolve.service';

const cREObservationsRoute: Routes = [
  {
    path: '',
    component: CREObservationsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CREObservationsDetailComponent,
    resolve: {
      cREObservations: CREObservationsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CREObservationsUpdateComponent,
    resolve: {
      cREObservations: CREObservationsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CREObservationsUpdateComponent,
    resolve: {
      cREObservations: CREObservationsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default cREObservationsRoute;
