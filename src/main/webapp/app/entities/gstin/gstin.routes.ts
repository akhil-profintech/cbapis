import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { GstinComponent } from './list/gstin.component';
import { GstinDetailComponent } from './detail/gstin-detail.component';
import { GstinUpdateComponent } from './update/gstin-update.component';
import GstinResolve from './route/gstin-routing-resolve.service';

const gstinRoute: Routes = [
  {
    path: '',
    component: GstinComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GstinDetailComponent,
    resolve: {
      gstin: GstinResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GstinUpdateComponent,
    resolve: {
      gstin: GstinResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GstinUpdateComponent,
    resolve: {
      gstin: GstinResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default gstinRoute;
