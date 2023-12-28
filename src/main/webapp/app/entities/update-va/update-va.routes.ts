import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { UpdateVAComponent } from './list/update-va.component';
import { UpdateVADetailComponent } from './detail/update-va-detail.component';
import { UpdateVAUpdateComponent } from './update/update-va-update.component';
import UpdateVAResolve from './route/update-va-routing-resolve.service';

const updateVARoute: Routes = [
  {
    path: '',
    component: UpdateVAComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UpdateVADetailComponent,
    resolve: {
      updateVA: UpdateVAResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UpdateVAUpdateComponent,
    resolve: {
      updateVA: UpdateVAResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UpdateVAUpdateComponent,
    resolve: {
      updateVA: UpdateVAResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default updateVARoute;
