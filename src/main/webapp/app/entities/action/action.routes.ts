import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ActionComponent } from './list/action.component';
import { ActionDetailComponent } from './detail/action-detail.component';
import { ActionUpdateComponent } from './update/action-update.component';
import ActionResolve from './route/action-routing-resolve.service';

const actionRoute: Routes = [
  {
    path: '',
    component: ActionComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ActionDetailComponent,
    resolve: {
      action: ActionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ActionUpdateComponent,
    resolve: {
      action: ActionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ActionUpdateComponent,
    resolve: {
      action: ActionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default actionRoute;
