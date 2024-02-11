import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { TenantDetailsComponent } from './list/tenant-details.component';
import { TenantDetailsDetailComponent } from './detail/tenant-details-detail.component';
import { TenantDetailsUpdateComponent } from './update/tenant-details-update.component';
import TenantDetailsResolve from './route/tenant-details-routing-resolve.service';

const tenantDetailsRoute: Routes = [
  {
    path: '',
    component: TenantDetailsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TenantDetailsDetailComponent,
    resolve: {
      tenantDetails: TenantDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TenantDetailsUpdateComponent,
    resolve: {
      tenantDetails: TenantDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TenantDetailsUpdateComponent,
    resolve: {
      tenantDetails: TenantDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default tenantDetailsRoute;
