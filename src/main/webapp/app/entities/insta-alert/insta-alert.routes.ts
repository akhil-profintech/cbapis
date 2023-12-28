import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { InstaAlertComponent } from './list/insta-alert.component';
import { InstaAlertDetailComponent } from './detail/insta-alert-detail.component';
import { InstaAlertUpdateComponent } from './update/insta-alert-update.component';
import InstaAlertResolve from './route/insta-alert-routing-resolve.service';

const instaAlertRoute: Routes = [
  {
    path: '',
    component: InstaAlertComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InstaAlertDetailComponent,
    resolve: {
      instaAlert: InstaAlertResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InstaAlertUpdateComponent,
    resolve: {
      instaAlert: InstaAlertResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InstaAlertUpdateComponent,
    resolve: {
      instaAlert: InstaAlertResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default instaAlertRoute;
