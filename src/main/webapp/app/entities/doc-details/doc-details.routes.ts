import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { DocDetailsComponent } from './list/doc-details.component';
import { DocDetailsDetailComponent } from './detail/doc-details-detail.component';
import { DocDetailsUpdateComponent } from './update/doc-details-update.component';
import DocDetailsResolve from './route/doc-details-routing-resolve.service';

const docDetailsRoute: Routes = [
  {
    path: '',
    component: DocDetailsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DocDetailsDetailComponent,
    resolve: {
      docDetails: DocDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DocDetailsUpdateComponent,
    resolve: {
      docDetails: DocDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DocDetailsUpdateComponent,
    resolve: {
      docDetails: DocDetailsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default docDetailsRoute;
