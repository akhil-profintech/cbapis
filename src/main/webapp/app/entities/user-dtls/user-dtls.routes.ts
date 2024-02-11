import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { UserDtlsComponent } from './list/user-dtls.component';
import { UserDtlsDetailComponent } from './detail/user-dtls-detail.component';
import { UserDtlsUpdateComponent } from './update/user-dtls-update.component';
import UserDtlsResolve from './route/user-dtls-routing-resolve.service';

const userDtlsRoute: Routes = [
  {
    path: '',
    component: UserDtlsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserDtlsDetailComponent,
    resolve: {
      userDtls: UserDtlsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UserDtlsUpdateComponent,
    resolve: {
      userDtls: UserDtlsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UserDtlsUpdateComponent,
    resolve: {
      userDtls: UserDtlsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default userDtlsRoute;
