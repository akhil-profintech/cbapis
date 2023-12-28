import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { DisbursementComponent } from './list/disbursement.component';
import { DisbursementDetailComponent } from './detail/disbursement-detail.component';
import { DisbursementUpdateComponent } from './update/disbursement-update.component';
import DisbursementResolve from './route/disbursement-routing-resolve.service';

const disbursementRoute: Routes = [
  {
    path: '',
    component: DisbursementComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DisbursementDetailComponent,
    resolve: {
      disbursement: DisbursementResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DisbursementUpdateComponent,
    resolve: {
      disbursement: DisbursementResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DisbursementUpdateComponent,
    resolve: {
      disbursement: DisbursementResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default disbursementRoute;
