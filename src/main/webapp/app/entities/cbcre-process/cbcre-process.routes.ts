import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { CBCREProcessComponent } from './list/cbcre-process.component';
import { CBCREProcessDetailComponent } from './detail/cbcre-process-detail.component';
import { CBCREProcessUpdateComponent } from './update/cbcre-process-update.component';
import CBCREProcessResolve from './route/cbcre-process-routing-resolve.service';

const cBCREProcessRoute: Routes = [
  {
    path: '',
    component: CBCREProcessComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CBCREProcessDetailComponent,
    resolve: {
      cBCREProcess: CBCREProcessResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CBCREProcessUpdateComponent,
    resolve: {
      cBCREProcess: CBCREProcessResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CBCREProcessUpdateComponent,
    resolve: {
      cBCREProcess: CBCREProcessResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default cBCREProcessRoute;
