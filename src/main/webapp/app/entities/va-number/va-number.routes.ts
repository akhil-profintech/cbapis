import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { VANumberComponent } from './list/va-number.component';
import { VANumberDetailComponent } from './detail/va-number-detail.component';
import { VANumberUpdateComponent } from './update/va-number-update.component';
import VANumberResolve from './route/va-number-routing-resolve.service';

const vANumberRoute: Routes = [
  {
    path: '',
    component: VANumberComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VANumberDetailComponent,
    resolve: {
      vANumber: VANumberResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VANumberUpdateComponent,
    resolve: {
      vANumber: VANumberResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VANumberUpdateComponent,
    resolve: {
      vANumber: VANumberResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default vANumberRoute;
