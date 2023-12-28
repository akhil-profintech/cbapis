import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ProspectRequestComponent } from './list/prospect-request.component';
import { ProspectRequestDetailComponent } from './detail/prospect-request-detail.component';
import { ProspectRequestUpdateComponent } from './update/prospect-request-update.component';
import ProspectRequestResolve from './route/prospect-request-routing-resolve.service';

const prospectRequestRoute: Routes = [
  {
    path: '',
    component: ProspectRequestComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProspectRequestDetailComponent,
    resolve: {
      prospectRequest: ProspectRequestResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProspectRequestUpdateComponent,
    resolve: {
      prospectRequest: ProspectRequestResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProspectRequestUpdateComponent,
    resolve: {
      prospectRequest: ProspectRequestResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default prospectRequestRoute;
