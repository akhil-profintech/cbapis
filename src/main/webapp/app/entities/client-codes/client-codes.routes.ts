import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ClientCodesComponent } from './list/client-codes.component';
import { ClientCodesDetailComponent } from './detail/client-codes-detail.component';
import { ClientCodesUpdateComponent } from './update/client-codes-update.component';
import ClientCodesResolve from './route/client-codes-routing-resolve.service';

const clientCodesRoute: Routes = [
  {
    path: '',
    component: ClientCodesComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ClientCodesDetailComponent,
    resolve: {
      clientCodes: ClientCodesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ClientCodesUpdateComponent,
    resolve: {
      clientCodes: ClientCodesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ClientCodesUpdateComponent,
    resolve: {
      clientCodes: ClientCodesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default clientCodesRoute;
